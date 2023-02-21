package com.musclebeach.emp.controller;


import com.musclebeach.coachtime.model.CoachTimeService;
import com.musclebeach.coachtime.model.CoachTimeVO;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.emp.model.EmpService;
import com.musclebeach.emp.model.EmpVO;
import org.springframework.context.ApplicationContext;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

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

            // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
            // ●1) 登入你的Gmail的:
            // ●2) 點選【管理你的 Google 帳戶】
            // ●3) 點選左側的【安全性】

            // ●4) 完成【兩步驟驗證】的所有要求如下:
            // ●4-1) (請自行依照步驟要求操作之.....)

            // ●5) 完成【應用程式密碼】的所有要求如下:
            // ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
            // ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
            // ●5-3) 最後按【產生】密碼
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

            // 設定信中的主旨
            message.setSubject(subject);
            // 設定信中的內容
            message.setText(messageText);

            Transport.send(message);
            System.out.println("傳送成功!");
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }
}

@MultipartConfig
@WebServlet("/back-end/emp/emp.do")
public class EmpServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final EmpService empService = context.getBean(EmpService.class);

    private final CoachTimeService coachTimeService = context.getBean(CoachTimeService.class);
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("search_By_Name".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorName = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorName", errorName);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String empName = req.getParameter("empName").trim();
            if (empName == null || (empName.trim()).length() == 0) {
                errorName.add("請輸入員工姓名");
            }
            // Send the use back to the form, if there were errors
            if (!errorName.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            List<EmpVO> list = empService.getEmpByName(empName);
            if (list.isEmpty()) {
                errorName.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorName.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/emp/listOneByName.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorID = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorID", errorID);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("empID");
            if (str == null || (str.trim()).length() == 0) {
                errorID.add("請輸入員工編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer empID = null;
            try {
                empID = Integer.valueOf(str);
            } catch (Exception e) {
                errorID.add("員工編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            EmpVO empVO = empService.getOneEmp(empID);
            if (empVO == null) {
                errorID.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/emp/listOneEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer empID = Integer.valueOf(req.getParameter("empID"));

            /*************************** 2.開始查詢資料 ****************************************/
            EmpVO empVO = empService.getOneEmp(empID);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/emp/update_emp_input2.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer empID = Integer.valueOf(req.getParameter("empID").trim());

            String password = req.getParameter("password").trim();
            String passwordReg = "^[(a-zA-Z0-9)]{2,10}$";
            if (password == null || password.trim().length() == 0) {
                errorMsgs.add("員工密碼: 請勿空白");
            } else if (!password.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工密碼: 只能是英文字母、數字, 且長度必需在2到10之間");
            }

            String empName = req.getParameter("empName").trim();
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (empName == null || empName.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if (!empName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            java.sql.Date hiredate = null;
            try {
                hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
            } catch (IllegalArgumentException e) {
                hiredate = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            java.sql.Date empBirthday = null;
            try {
                empBirthday = java.sql.Date.valueOf(req.getParameter("empBirthday").trim());
            } catch (IllegalArgumentException e) {
                empBirthday = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            String empPhone = req.getParameter("empPhone").trim();
            String empPhoneReg = "^09[(0-9)]{8}$";
            if (empPhone == null || empPhone.trim().length() == 0) {
                errorMsgs.add("員工電話: 請勿空白");
            } else if (!empPhone.trim().matches(empPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工電話: 請輸入09開頭的10位數字");
            }

            String empMail = req.getParameter("empMail").trim();
            String empMailReg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            if (empMail == null || empMail.trim().length() == 0) {
                errorMsgs.add("員工信箱: 請勿空白");
            } else if (!empMail.trim().matches(empMailReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工信箱: 請輸入正確格式的信箱");
            }

            Integer empStatus = Integer.valueOf(req.getParameter("empStatus").trim());

            Integer coachPrice = null;
            try {
                coachPrice = Integer.valueOf(req.getParameter("coachPrice").trim());
            } catch (NumberFormatException e) {
                coachPrice = 0;
                errorMsgs.add("教練薪水請填數字.");
            }

            Part part = req.getPart("empImg"); // 來自於上面的form表單
            InputStream in = part.getInputStream();
            byte[] b = new byte[in.available()];
            if (b.length == 0) {
                EmpVO empVO = empService.getOneEmp(empID);
                b = empVO.getEmpImg();
            } else {
                in.read(b);
                in.close();
            }

            String coachContent = req.getParameter("coachContent").trim();

            EmpVO empVO = new EmpVO();
            empVO.getEmpImg();
            empVO.setEmpID(empID);
            empVO.setPassword(password);
            empVO.setEmpName(empName);
            empVO.setHiredate(hiredate);
            empVO.setEmpBirthday(empBirthday);
            empVO.setEmpPhone(empPhone);
            empVO.setEmpMail(empMail);
            empVO.setEmpStatus(empStatus);
            empVO.setCoachPrice(coachPrice);
            empVO.setCoachContent(coachContent);
            empVO.setEmpImg(b);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input2.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/
            empVO = empService.updateEmp(empID, password, empName, hiredate, empBirthday, empPhone, empMail, empStatus,
                    coachPrice, b, coachContent);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req

            // 更新session資料
            HttpSession session = req.getSession(false);
            EmpVO empVO2 = (EmpVO) session.getAttribute("empVO");
            empVO2 = empService.getOneEmp(empVO2.getEmpID());
            session.setAttribute("empVO", empVO2);

            String url = "/back-end/emp/listAllEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String password = req.getParameter("password").trim();
            String passwordReg = "^[(a-zA-Z0-9)]{2,10}$";
            if (password == null || password.trim().length() == 0) {
                errorMsgs.add("員工密碼: 請勿空白");
            } else if (!password.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工密碼: 只能是英文字母、數字, 且長度必需在2到10之間");
            }

            String empName = req.getParameter("empName").trim();
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (empName == null || empName.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if (!empName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            java.sql.Date hiredate = null;
            try {
                hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
            } catch (IllegalArgumentException e) {
                hiredate = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            java.sql.Date empBirthday = null;
            try {
                empBirthday = java.sql.Date.valueOf(req.getParameter("empBirthday").trim());
            } catch (IllegalArgumentException e) {
                empBirthday = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            String empPhone = req.getParameter("empPhone").trim();
            String empPhoneReg = "^09[(0-9)]{8}$";
            if (empPhone == null || empPhone.trim().length() == 0) {
                errorMsgs.add("員工電話: 請勿空白");
            } else if (!empPhone.trim().matches(empPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工電話: 請輸入09開頭的10位數字");
            }

            String empMail = req.getParameter("empMail").trim();
            String empMailReg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            if (empMail == null || empMail.trim().length() == 0) {
                errorMsgs.add("員工信箱: 請勿空白");
            } else if (!empMail.trim().matches(empMailReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工信箱: 請輸入正確格式的信箱");
            }

            Integer empStatus = Integer.valueOf(req.getParameter("empStatus").trim());

            Integer coachPrice = null;
            try {
                coachPrice = Integer.valueOf(req.getParameter("coachPrice").trim());
            } catch (NumberFormatException e) {
                coachPrice = 0;
                errorMsgs.add("教練薪水請填數字.");
            }

            Part part = req.getPart("empImg"); // 來自於上面的form表單
            InputStream in = part.getInputStream();
            byte[] b = new byte[in.available()];
            in.read(b);
            in.close();

            String coachContent = req.getParameter("coachContent").trim();

            EmpVO empVO = new EmpVO();
            empVO.setPassword(password);
            empVO.setEmpName(empName);
            empVO.setHiredate(hiredate);
            empVO.setEmpBirthday(empBirthday);
            empVO.setEmpPhone(empPhone);
            empVO.setEmpMail(empMail);
            empVO.setEmpStatus(empStatus);
            empVO.setCoachPrice(coachPrice);
            empVO.setEmpImg(b);
            empVO.setCoachContent(coachContent);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/
            empVO = empService.addEmp(password, empName, hiredate, empBirthday, empPhone, empMail, empStatus, coachPrice, b,
                    coachContent);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/emp/listAllEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }

        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer empID = Integer.valueOf(req.getParameter("empID"));

            /*************************** 2.開始刪除資料 ***************************************/
            empService.deleteEmp(empID);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/emp/listAllEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }

        if ("getImg".equals(action)) {
            ServletOutputStream out = res.getOutputStream();
            String empIDString = req.getParameter("empID");
            EmpVO empVO = empService.getOneEmp(Integer.parseInt(empIDString));
            res.setContentType("image/jpg");
            res.setContentLength(empVO.getEmpImg().length);
            out.write(empVO.getEmpImg());
            out.close();
        }

        if ("login".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();

            req.setAttribute("errorMsgs", errorMsgs);

            String str = req.getParameter("empID");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入員工編號");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/backendLogin.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer empID = null;
            try {
                empID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("員工編號格式不正確");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/backendLogin.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            String password = req.getParameter("password").trim();

            EmpVO empVO = empService.getOneEmp(empID);
            if (empVO == null) {
                errorMsgs.add("查無資料");
            }

            if (!password.equals(empVO.getPassword())) {
                errorMsgs.add("密碼錯誤");
            }

            if(empVO.getEmpStatus() == 0){
                errorMsgs.add("此員工已離職");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/backendLogin.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            HttpSession session = req.getSession();
            session.setAttribute("empVO", empVO);

            String url = "/back-end/emp/back_index.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);

        }

        if ("logout".equals(action)) {
            HttpSession session = req.getSession();
            // 清除資料
            session.invalidate();
            String url = "/back-end/emp/backendLogin.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);

        }

        if ("update_password".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer empID = Integer.valueOf(req.getParameter("empID").trim());
            String oldPassword = req.getParameter("old_password").trim();
            String newPassword = req.getParameter("new_password").trim();
            String passwordReg = "^[(a-zA-Z0-9)]{2,10}$";
            if (newPassword == null || newPassword.trim().length() == 0) {
                errorMsgs.add("新的密碼: 請勿空白");
            } else if (!newPassword.matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("新的密碼: 只能是英文字母、數字, 且長度必需在2到10之間");
            }

            String repeatPassword = req.getParameter("repeat_password").trim();
            if (!newPassword.equals(repeatPassword)) {
                errorMsgs.add("確認密碼: 不一致");
            }

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("old_password", oldPassword); // 含有輸入格式錯誤的empVO物件,也存入req
                req.setAttribute("new_password", newPassword);
                req.setAttribute("repeat_password", repeatPassword);
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_password.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/
            EmpVO empVO = empService.getOneEmp(empID);

            if (!oldPassword.equals(empVO.getPassword())) {
                errorMsgs.add("舊的密碼: 不一致");
            }

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("old_password", oldPassword); // 含有輸入格式錯誤的empVO物件,也存入req
                req.setAttribute("new_password", newPassword);
                req.setAttribute("repeat_password", repeatPassword);
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_password.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            empService.updatePassword(empID, newPassword);
            empVO = empService.getOneEmp(empID);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/emp/select_page.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("sendMail".equals(action)) {
            String to = "barry111160@gmail.com";

            String subject = "密碼通知";

            String ch_name = "peter1";
            String passRandom = "111";
            String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";

            MailService mailService = new MailService();
            mailService.sendMail(to, subject, messageText);
        }

        if ("UseCoachOrder_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorID = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorID", errorID);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("empID");
            if (str == null || (str.trim()).length() == 0) {
                errorID.add("請輸入員工編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer empID = null;
            try {
                empID = Integer.valueOf(str);
            } catch (Exception e) {
                errorID.add("員工編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            EmpVO empVO = empService.getOneEmp(empID);
            if (empVO == null) {
                errorID.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
            String url = "/front-end/emp/addemp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getCoachData_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorID = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorID", errorID);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("empID");
            if (str == null || (str.trim()).length() == 0) {
                errorID.add("請輸入員工編號");
            }

            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/coachList.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer empID = null;
            try {
                empID = Integer.valueOf(str);
            } catch (Exception e) {
                errorID.add("員工編號格式不正確");
            }

            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/coachList.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }


            /*************************** 2.開始查詢資料 *****************************************/
            EmpVO empVO = empService.getOneEmp(empID);

            CoachTimeVO coachTimeVO = coachTimeService.getCoachDate(empID);
            CoachTimeVO coachTimeVO2 = coachTimeService.getCoachTime(empID);
            List<CoachTimeVO> list = coachTimeService.getAllCoachDate(empID);
            ArrayList te = new ArrayList();
            for (CoachTimeVO ctime : list) {
                te.add(ctime.getCoachDate());
            }
            if (empVO == null) {
                errorID.add("查無資料");
            }

            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/coachList.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
            req.setAttribute("coachTimeVO", coachTimeVO);
            req.setAttribute("coachTimeVO2", te);
            String url = "/front-end/coach/coachListOne.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }


        if ("getCoach_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorID = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorID", errorID);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("empID");
            if (str == null || (str.trim()).length() == 0) {
                errorID.add("請輸入員工編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer empID = null;
            try {
                empID = Integer.valueOf(str);
            } catch (Exception e) {
                errorID.add("員工編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            EmpVO empVO = empService.getOneEmp(empID);
            if (empVO == null) {
                errorID.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorID.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/emp/listOneEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }


//		if("UpdateCoach".equals(action)) {
//			Integer empID = Integer.valueOf(req.getParameter("empID").trim());
//			EmpService empSvc = new EmpService();
//			empSvc.updateStatus(empID, 0);
//			
////			String url = "/back-end/emp/listAllEmp.jsp";
////			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
////			successView.forward(req, res);	
////			
////			
////			
////			
////			res.setContentType("text/html; charset=UTF-8");
////			req.setCharacterEncoding("UTF-8");
////			String result = "1";
////			res.getWriter().append(result);
//			
//		}


    }
}
