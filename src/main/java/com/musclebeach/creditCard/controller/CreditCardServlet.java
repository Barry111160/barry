package com.musclebeach.creditCard.controller;


import com.musclebeach.backstage.service.MemberService;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.creditCard.model.CreditCardService;
import com.musclebeach.creditCard.model.CreditCardVO;
import com.musclebeach.mem.model.MemService;
import com.musclebeach.mem.model.MemVO;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/front-end/creditCard/creditCard.do")
public class CreditCardServlet extends HttpServlet {
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final MemService memService = ctx.getBean(MemService.class);
    private final MemberService memberService = ctx.getBean(MemberService.class);
    private final CreditCardService creditCardService = ctx.getBean(CreditCardService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String action1 = req.getParameter("action1");


        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String str = req.getParameter("ccID");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入信用卡編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/creditCard/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            Integer ccID = null;
            try {
                ccID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("信用卡編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/creditCard/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            CreditCardVO creditCardVO = creditCardService.getOneCard(ccID);
            if (creditCardVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/creditCard/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("creditCardVO", creditCardVO);
            String url = "/front-end/creditCard/listOneCreditCard.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }


        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer ccID = Integer.valueOf(req.getParameter("ccID"));

            /***************************2.開始查詢資料****************************************/
            CreditCardVO creditCardVO = creditCardService.getOneCard(ccID);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("creditCardVO", creditCardVO);
            String url = "/front-end/creditCard/update_creditCard_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("updatememberaccessanddate".equals(action1)) {
            Integer memID = Integer.valueOf(req.getParameter("memID"));
            Boolean updateMemberAccess = memberService.updateMemberAccess(memID);
            MemVO memVO = memService.getOneMem(memID);
            req.getSession().setAttribute("memVO", memVO);
        }

        if ("update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer ccID = Integer.valueOf(req.getParameter("ccID").trim());

            Integer memID = Integer.valueOf(req.getParameter("memID").trim());

            String ccNumber = req.getParameter("ccNumber");
            String ccNumberReg = "^[(0-9)]{16}$";
            if (ccNumber == null || ccNumber.trim().length() == 0) {
                errorMsgs.add("信用卡號: 請勿空白");
            } else if (!ccNumber.trim().matches(ccNumberReg)) {
                errorMsgs.add("信用卡號: 只能是數字且長度為16碼");
            }

            String ccName = req.getParameter("ccName").trim();
            if (ccName == null || ccName.trim().length() == 0) {
                errorMsgs.add("持卡人姓名請勿空白");
            }
            String ccTimeReg = "^[(0-9)]{4}$";
            String ccTime = req.getParameter("ccTime").trim();
            if (ccTime == null || ccTime.trim().length() == 0) {
                errorMsgs.add("信用卡期限請勿空白");
            } else if (!ccTimeReg.trim().matches(ccTimeReg)) {
                errorMsgs.add("信用卡期限: 只能是數字且長度為4碼");
            }
            String ccvcReg = "^[(0-9)]{3}$";
            String ccvc = req.getParameter("ccvc").trim();
            if (ccvc == null || ccvc.trim().length() == 0) {
                errorMsgs.add("驗證碼請勿空白");
            } else if (!ccvcReg.trim().matches(ccvcReg)) {
                errorMsgs.add("驗證碼: 只能是數字且長度為3碼");
            }


            CreditCardVO creditCardVO = new CreditCardVO();
            creditCardVO.setCcID(ccID);
            creditCardVO.setMemID(memID);
            creditCardVO.setCcNumber(ccNumber);
            creditCardVO.setCcName(ccName);
            creditCardVO.setCcTime(ccTime);
            creditCardVO.setCcvc(ccvc);


            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("creditCardVO", creditCardVO);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/creditCard/update_creditCard_input.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            creditCardVO = creditCardService.updateCard(ccID, memID, ccNumber, ccName, ccTime, ccvc);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("creditCardVO", creditCardVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/front-end/creditCard/listOneCreditCard.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            Integer memID = Integer.valueOf(req.getParameter("memID").trim());

            String ccNumber = req.getParameter("ccNumber");
            String ccNumberReg = "^[(0-9)]{16}$";
            if (ccNumber == null || ccNumber.trim().length() == 0) {
                errorMsgs.add("信用卡號: 請勿空白");
            } else if (!ccNumber.trim().matches(ccNumberReg)) {
                errorMsgs.add("信用卡號: 只能是數字且長度為16碼");
            }

            String ccName = req.getParameter("ccName").trim();
            if (ccName == null || ccName.trim().length() == 0) {
                errorMsgs.add("持卡人姓名請勿空白");
            }
            String ccTime = req.getParameter("ccTime").trim();
            String ccTimeReg = "^[(0-9)]{5}$";

            if (ccTime == null || ccTime.trim().length() == 0) {
                errorMsgs.add("信用卡期限請勿空白");
            }
//				else if(!ccTimeReg.trim().matches(ccTimeReg)) { 
//					errorMsgs.add("信用卡期限: 只能是數字且長度為4碼");
//	            }

            String ccvc = req.getParameter("ccvc").trim();
            String ccvcReg = "^[(0-9)]{4}$";

            if (ccvc == null || ccvc.trim().length() == 0) {
                errorMsgs.add("驗證碼請勿空白");
            }
//				else if(!ccvcReg.trim().matches(ccvcReg)) { 
//					errorMsgs.add("驗證碼: 只能是數字且長度為3碼");
//	            }


            CreditCardVO creditCardVO = new CreditCardVO();
            creditCardVO.setMemID(memID);
            creditCardVO.setCcNumber(ccNumber);
            creditCardVO.setCcName(ccName);
            creditCardVO.setCcTime(ccTime);
            creditCardVO.setCcvc(ccvc);


            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("creditCardVO", creditCardVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/creditCard/addCreditCard.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.開始新增資料***************************************/
            creditCardVO = creditCardService.addCard(memID, ccNumber, ccName, ccTime, ccvc);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/front-end/product/shop.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }

        if ("upgradeByRoom".equals(action)) { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            Integer memID = Integer.valueOf(req.getParameter("memID").trim());

            String ccNumber = req.getParameter("ccNumber");
            String ccNumberReg = "^[(0-9)]{16}$";
            if (ccNumber == null || ccNumber.trim().length() == 0) {
                errorMsgs.add("信用卡號: 請勿空白");
            } else if (!ccNumber.trim().matches(ccNumberReg)) {
                errorMsgs.add("信用卡號: 只能是數字且長度為16碼");
            }

            String ccName = req.getParameter("ccName").trim();
            if (ccName == null || ccName.trim().length() == 0) {
                errorMsgs.add("持卡人姓名請勿空白");
            }
            String ccTime = req.getParameter("ccTime").trim();
            String ccTimeReg = "^[(0-9)]{5}$";

            if (ccTime == null || ccTime.trim().length() == 0) {
                errorMsgs.add("信用卡期限請勿空白");
            }
//				else if(!ccTimeReg.trim().matches(ccTimeReg)) {
//					errorMsgs.add("信用卡期限: 只能是數字且長度為4碼");
//	            }

            String ccvc = req.getParameter("ccvc").trim();
            String ccvcReg = "^[(0-9)]{4}$";

            if (ccvc == null || ccvc.trim().length() == 0) {
                errorMsgs.add("驗證碼請勿空白");
            }
//				else if(!ccvcReg.trim().matches(ccvcReg)) {
//					errorMsgs.add("驗證碼: 只能是數字且長度為3碼");
//	            }


            CreditCardVO creditCardVO = new CreditCardVO();
            creditCardVO.setMemID(memID);
            creditCardVO.setCcNumber(ccNumber);
            creditCardVO.setCcName(ccName);
            creditCardVO.setCcTime(ccTime);
            creditCardVO.setCcvc(ccvc);


            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("creditCardVO", creditCardVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/creditCard/addCreditCard.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.開始新增資料***************************************/
            creditCardVO = creditCardService.addCard(memID, ccNumber, ccName, ccTime, ccvc);
            MemVO memVO = memService.getOneMem(memID);
            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            req.setAttribute("memVO", memVO);
            HttpSession session = req.getSession(false);
            MemVO memVO2 = (MemVO) session.getAttribute("memVO");
            memVO2 = memService.getOneMem(memVO2.getMemID());
            session.setAttribute("memVO", memVO2);

            String url = "/front-end/room/listAllRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }


        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數***************************************/
            Integer ccID = Integer.valueOf(req.getParameter("ccID"));

            /***************************2.開始刪除資料***************************************/
            creditCardService.deleteCard(ccID);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/front-end/creditCard/listAllCreditCard.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
    }
}

