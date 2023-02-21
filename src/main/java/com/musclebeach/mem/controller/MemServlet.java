package com.musclebeach.mem.controller;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.mem.model.MemService;
import com.musclebeach.mem.model.MemVO;
import org.springframework.context.ApplicationContext;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@WebServlet("/back-end/member/mem.do")
public class MemServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final MemService memService = context.getBean(MemService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("login".equals(action)) { // from login.jsp
            List<String> errorAccount = new LinkedList<String>();
            req.setAttribute("errorAccount", errorAccount);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            if (account == null || account.trim().length() == 0) {
                errorAccount.add("帳號或密碼不可空白");
            } else if (password == null || password.trim().length() == 0) {
                errorAccount.add("帳號或密碼不可空白");
            }
            if (!errorAccount.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始比對資料 ***************************************/

            MemVO memVO = memService.getAccount(account);
            if (memVO == null) {
                errorAccount.add("查無此帳號");
            } else if (!password.equals(memVO.getPassword())) {
                errorAccount.add("密碼輸入錯誤");
            } else if (memVO.getMemStatus() == 0) {
                errorAccount.add("帳號尚未啟用，請至註冊信箱點擊驗證連結");
            } else if (memVO.getMemStatus() == 2) {
                errorAccount.add("此使用者已停權，請洽客服");
            }
            if (!errorAccount.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
                failureView.forward(req, res);
                return;
            }

            /********** 比對會籍效期 **********/
            if (memVO.getMemAccess() == 1) {
                // 當前日期
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date date = calendar.getTime();
                // 會員會籍
                java.sql.Date sqlDate = memVO.getMembership();
                Date utilDate = new Date(sqlDate.getTime());
//				System.out.println(date);
//				System.out.println(sqlDate);
                if (date.compareTo(utilDate) > 0) {
                    memService.updateMembership(memVO.getMemID());
                }
            }

            /*************************** 3.確認帳密,準備轉交(Send the Success view) ***********/
            HttpSession session = req.getSession(false);
            MemVO memVO2 = memService.getAccount(account);
            session.setAttribute("memVO", memVO2);

            String url = req.getContextPath() + "/front-end/indexlogin.jsp";
            res.sendRedirect(url);
        }

        if ("logout".equals(action)) { // from 前台頁面
            HttpSession session = req.getSession();
            // 清除資料
            session.invalidate();
            String url = req.getContextPath() + "/front-end/index.html";
            res.sendRedirect(url);
        }

        if ("forget_password".equals(action)) { // from forgetPassword.jsp

            List<String> errorAccount = new LinkedList<String>();
            req.setAttribute("errorAccount", errorAccount);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String account = req.getParameter("account");
            String memMail = req.getParameter("memMail");
            if (account == null || account.trim().length() == 0) {
                errorAccount.add("帳號或信箱不可空白");
            } else if (memMail == null || memMail.trim().length() == 0) {
                errorAccount.add("帳號或密碼不可空白");
            }
            if (!errorAccount.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/forgetPassword.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始比對資料 ***************************************/

            MemVO memVO = memService.getAccount(account);
            if (memVO == null) {
                errorAccount.add("查無此帳號");
            } else if (!memMail.equals(memVO.getMemMail())) {
                errorAccount.add("信箱輸入錯誤");
            }
            if (!errorAccount.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/forgetPassword.jsp");
                failureView.forward(req, res);
                return;
            }
            // 傳送重設密碼信
            String to = memVO.getMemMail();
            String subject = "重設密碼通知";
            String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            String passRandom = "";
            for (int i = 0; i < 8; i++)
                passRandom += chars.charAt((int) (Math.random() * 62));
            String messageText = "Hello! " + memVO.getMemName() + "," + "\n"
                    + "此為系統預設臨時密碼: " + passRandom + "\n"
                    + "為了確保您的帳戶安全，請立即登入並重設密碼。" + "\n" + "\n"
                    + "若有任何問題，歡迎隨時與我們聯絡：musclebeachservice@gmail.com";

            MailService mailService = new MailService();
            mailService.sendMail(to, subject, messageText);

            /*************************** 3.確認帳密,準備轉交(Send the Success view) ***********/
            // 重設密碼
            memService.updatePassWord(memVO.getMemID(), passRandom);

            String url = req.getContextPath() + "/front-end/index.html";
            res.sendRedirect(url);
        }

        if ("insert".equals(action)) { // from register.jsp

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String memName = req.getParameter("memName");
            String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,}$";
            String account = req.getParameter("account").trim();
            String password = req.getParameter("password").trim();
            String memPhone = req.getParameter("memPhone").trim();
            String birthday = req.getParameter("memBirthday").trim();
            String memMail = req.getParameter("memMail").trim();
            String memAddress = req.getParameter("memAddress").trim();

            if (!memName.trim().matches(memNameReg)) {
                errorMsgs.add("姓名長度輸入錯誤，且不含任何符號");
            }

            java.sql.Date memBirthday = null;
            try {
                memBirthday = java.sql.Date.valueOf(birthday);
            } catch (IllegalArgumentException e) {
                memBirthday = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("生日格式輸入錯誤");
            }

            MemVO memVO = new MemVO();
            memVO.setMemName(memName);
            memVO.setAccount(account);
            memVO.setPassword(password);
            memVO.setMemPhone(memPhone);
            memVO.setMemBirthday(memBirthday);
            memVO.setMemAddress(memAddress);
            memVO.setMemMail(memMail);
            memVO.setMemStatus(0); // 預設為 0：未啟用
            memVO.setMemAccess(0); // 預設為 0：一般會員

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memVO", memVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/register.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/

            memVO = memService.getAccount(account);
            if (memVO != null) {
                errorMsgs.add("帳號已被註冊");
            }
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memVO", memVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/register.jsp");
                failureView.forward(req, res);
                return;
            }
            memVO = memService.addMem(memName, account, password, memPhone, memBirthday, memAddress, memMail, 0, 0);
            // 傳送註冊信
            String to = memMail;
            String subject = "歡迎加入 Muscle Beach！";
//		    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//			String passRandom = "";
//			for (int i = 0; i < 8; i++) 
//				passRandom += chars.charAt((int)(Math.random() * 62));
            String messageText = "Hello! " + memName + "，恭喜您成為 Muscle Beach 的一員！" + "\n"
                    + "我們會努力為您帶來更好的服務與商品。" + "\n" + "\n"
                    + "升級健身會員，可享更多優惠哦！" + "\n" + "\n";
//		    String info = "啟用帳號：<a href='http://127.0.0.1:8080/mailWeb1602/ActiveServlet?active="+ account +"'>點此啟用</a>";
            MailService mailService = new MailService();
            mailService.sendMail(to, subject, messageText);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = req.getContextPath() + "/front-end/member/login.jsp";
            res.sendRedirect(url);
        }

        if ("update".equals(action)) { // from mem_info_update.jsp

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            Integer memID = Integer.valueOf(req.getParameter("memID"));
            String memName = req.getParameter("memName");
            String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,}$";
            String account = req.getParameter("memAccount");
            String password = req.getParameter("password").trim();
            String memPhone = req.getParameter("memPhone").trim();
            String memMail = req.getParameter("memMail").trim();
            String memAddress = req.getParameter("memAddress").trim();

            if (!memName.trim().matches(memNameReg)) {
                errorMsgs.add("姓名長度輸入錯誤，且不含任何符號");
            }

            MemVO memVO = new MemVO();
            memVO.setMemID(memID);
            memVO.setMemName(memName);
            memVO.setAccount(account);
            memVO.setMemPhone(memPhone);
            memVO.setMemAddress(memAddress);
            memVO.setMemMail(memMail);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memVO", memVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/mem_info_update.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/

            memVO = memService.getAccount(account);
            if (!password.equals(memVO.getPassword())) {
                errorMsgs.add("密碼輸入錯誤");
            }
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memVO", memVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/mem_info_update.jsp");
                failureView.forward(req, res);
                return;
            }
            memVO = memService.updateMem(memID, memName, account, memPhone, memAddress, memMail);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("memVO", memVO);
            HttpSession session = req.getSession(false);
            MemVO memVO2 = (MemVO) session.getAttribute("memVO");
            memVO2 = memService.getOneMem(memVO2.getMemID());
            session.setAttribute("memVO", memVO2);

            String url = "/front-end/member/mem_info.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }


        if ("delete".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            Integer memID = Integer.valueOf(req.getParameter("memID"));

            /*************************** 2.開始新增資料 ***************************************/

            memService.deleteMem(memID);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/member/listAllMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("search_By_ID".equals(action)) { // from memPage.jsp 依會員編號查詢

            List<String> errorID = new LinkedList<String>();
            req.setAttribute("errorID", errorID);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String str = req.getParameter("memID");
            if (str == null || str.trim().length() == 0) {
                errorID.add("請輸入會員編號!");
            }

            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始查詢資料 ***************************************/
            Integer memID = Integer.valueOf(str);

            MemVO MemVO = memService.getOneMem(memID);
            if (MemVO == null) {
                errorID.add("查無此會員");
            }
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("MemVO", MemVO);
            String url = "/back-end/member/listOneMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("search_By_Name".equals(action)) { // from memPage.jsp 依會員姓名查詢

            List<String> errorName = new LinkedList<String>();
            req.setAttribute("errorName", errorName);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String memName = req.getParameter("memName");
            String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
            if (memName == null || memName.trim().length() == 0) {
                errorName.add("請輸入會員姓名!");
            } else if (!memName.trim().matches(memNameReg)) {
                errorName.add("格式錯誤");
            }
            if (!errorName.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始比對資料 ***************************************/

            List<MemVO> MemVO = memService.getByName(memName);
            if (MemVO.isEmpty()) {
                errorName.add("查無會員資料");
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }
            for (MemVO aMem : MemVO) {
//				System.out.println(aMem.getPassword());
                if (aMem == null || MemVO.isEmpty()) {
                    errorName.add("查無會員資料");
                }
            }
            if (!errorName.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("MemVO", MemVO);
            String url = "/back-end/member/listByName.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("search_By_Phone".equals(action)) { // from memPage.jsp 依會員電話查詢

            List<String> errorPhone = new LinkedList<String>();
            req.setAttribute("errorPhone", errorPhone);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String memPhone = req.getParameter("memPhone");
            if (memPhone == null || memPhone.trim().length() == 0) {
                errorPhone.add("請輸入會員電話!");
            }

            if (!errorPhone.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始查詢資料 ***************************************/

            MemVO MemVO = memService.getByPhone(memPhone);
            if (MemVO == null) {
                errorPhone.add("查無會員資料");
            }
            if (!errorPhone.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("MemVO", MemVO);
            String url = "/back-end/member/listOneMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("search_By_Birth".equals(action)) { // from memPage.jsp 依會員生日查詢

            List<String> errorBirth = new LinkedList<String>();
            req.setAttribute("errorBirth", errorBirth);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            java.sql.Date memBirth = null;
            try {
                memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
            } catch (IllegalArgumentException e) {
                errorBirth.add("請輸入會員生日!");
                errorBirth.add("正確格式，如：1999-01-01");
            }

            if (!errorBirth.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始查詢資料 ***************************************/

            MemVO MemVO = memService.getByBirth(memBirth);
            if (MemVO == null) {
                errorBirth.add("查無會員資料");
            }
            if (!errorBirth.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("MemVO", MemVO);
            String url = "/back-end/member/listOneMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }


    }
}

class MailService {

    // 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
    public void sendMail(String to, String subject, String messageText) {

        try {
            // 設定使用SSL連線至 Gmail smtp Server
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            final String myGmail = "hbh81167@gmail.com";
            final String myGmail_password = "qgnylnhyvqxlubit";
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myGmail, myGmail_password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myGmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            //設定信中的主旨
            message.setSubject(subject);
            //設定信中的內容
            message.setText(messageText);

            Transport.send(message);
            System.out.println("傳送成功!");
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }

}
