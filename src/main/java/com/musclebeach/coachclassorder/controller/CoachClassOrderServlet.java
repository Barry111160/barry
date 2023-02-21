package com.musclebeach.coachclassorder.controller;


import com.musclebeach.coachclassorder.model.CoachClassOrderService;
import com.musclebeach.coachclassorder.model.CoachClassOrderVO;
import com.musclebeach.coachtime.model.CoachTimeService;
import com.musclebeach.coachtime.model.CoachTimeVO;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.emp.model.EmpService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
@WebServlet("/back-end/coachclassorder/coach.do")
public class CoachClassOrderServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final CoachClassOrderService coachSvc = context.getBean(CoachClassOrderService.class);
    private final EmpService empSvc = context.getBean(EmpService.class);
    private final CoachTimeService coachTimeService = context.getBean(CoachTimeService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("orderid");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入教練訂單編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;
            }

            Integer orderid = null;
            try {
                orderid = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("教練訂單編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始查詢資料 *****************************************/

            CoachClassOrderVO coachClassOrderVO = coachSvc.getOneEmp(orderid);
            if (orderid == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;// �{�����_
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("coachClassOrderVO", coachClassOrderVO); // ��Ʈw���X��empVO����,�s�Jreq
            String url = "/back-end/coachclassorder/listOneCoachClassOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
            successView.forward(req, res);
        }


        if ("getMem_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("memid");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入教練訂單編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;// �{�����_
            }

            Integer memid = null;
            try {
                memid = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("教練訂單編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;// �{�����_
            }

            /*************************** 2.開始查詢資料 *****************************************/
            CoachClassOrderVO coachClassOrderVO = coachSvc.getOneEmp(memid);

            List<CoachClassOrderVO> coachClassOrderVO2 = coachSvc.getAllbyMem(memid);


            if (memid == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;// �{�����_
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("coachClassOrderVO2", coachClassOrderVO2);
            String url = "/back-end/coachclassorder/listAllCoachClassOrderByMEM.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getMem_For_DisplayFront".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("memid");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入教練訂單編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;// �{�����_
            }

            Integer memid = null;
            try {
                memid = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("教練訂單編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;// �{�����_
            }

            /*************************** 2.開始查詢資料 *****************************************/
            List<CoachClassOrderVO> coachClassOrderVO2 = coachSvc.getAllbyMem(memid);

            if (memid == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/coachclassorder/selectCoachClassOrderPage.jsp");
                failureView.forward(req, res);
                return;// �{�����_
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("coachClassOrderVO2", coachClassOrderVO2);
            String url = "/back-end/coachclassorder/memcoachclasslist.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
            successView.forward(req, res);
        }


        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************
             * 1.�����ШD�Ѽ�
             ****************************************/
            Integer orderid = Integer.valueOf(req.getParameter("orderid"));
            Integer empid = Integer.valueOf(req.getParameter("empID").trim());


            /***************************
             * 2.
             ****************************************/
            CoachClassOrderVO coachClassOrderVO = coachSvc.getOneEmp(orderid);
            CoachTimeVO coachTimeVO = coachTimeService.getCoachTime(empid);

            /***************************
             * 3.(Send the Success view)
             ************/
            req.setAttribute("coachClassOrderVO", coachClassOrderVO);
            req.setAttribute("coachTimeVO", coachTimeVO);
            String url = "/back-end/coachclassorder/updateCoachClassOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************
             * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
             **********************/
            Integer orderstatus = null;
            try {
                orderstatus = Integer.valueOf(req.getParameter("orderstatus").trim());
            } catch (NumberFormatException e) {
                orderstatus = 0;
                errorMsgs.add("訂單狀態錯誤");
            }

            if (orderstatus == 1) {

                Integer orderid = Integer.valueOf(req.getParameter("orderid").trim());
                Integer memid = null;
                try {
                    memid = Integer.valueOf(req.getParameter("memid").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("會員格式錯誤");
                    memid = 0;
                }

                java.sql.Date classTime = null;
                try {
                    classTime = java.sql.Date.valueOf(req.getParameter("classTime").trim());
                } catch (IllegalArgumentException e) {
                    classTime = new java.sql.Date(System.currentTimeMillis());
                    errorMsgs.add("時間格式錯誤");
                }
                Integer empid = null;
                try {
                    empid = Integer.valueOf(req.getParameter("empid").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("員工編號格式錯誤");
                    empid = 0;
                }
                String coachTime = req.getParameter("coachTime");


                CoachClassOrderVO coachClassOrderVO = new CoachClassOrderVO();
                coachClassOrderVO.setOrderID(orderid);
                coachClassOrderVO.setEmpID(empid);
                coachClassOrderVO.setMemID(memid);
                coachClassOrderVO.setClassTime(classTime);
                coachClassOrderVO.setOrderstatus(orderstatus);
//			coachClassOrderVO.setCoachclassperiod(coachclassperiod);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("coachClassOrderVO", coachClassOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/back-end/coachclassorder/updateCoachClassOrder.jsp");
                    failureView.forward(req, res);
                    return; // �{�����_
                }
                /*************************** * 2.�}�l�ק���*****************************************/
                coachClassOrderVO = coachSvc.updateEmp(orderid, empid, memid, classTime, orderstatus, coachTime);

                /**************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
                req.setAttribute("coachClassOrderVO", coachClassOrderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
                String url = "/back-end/coachclassorder/listOneCoachClassOrder.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
                successView.forward(req, res);
            } else if
//			-----------------------------------------------------
            (orderstatus == 0) {

                Integer orderid = Integer.valueOf(req.getParameter("orderid").trim());
                Integer memid = null;
                try {
                    memid = Integer.valueOf(req.getParameter("memid").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("會員格式錯誤");
                    memid = 0;
                }

                java.sql.Date classTime = null;
                try {
                    classTime = java.sql.Date.valueOf(req.getParameter("classTime").trim());
                } catch (IllegalArgumentException e) {
                    classTime = new java.sql.Date(System.currentTimeMillis());
                    errorMsgs.add("時間格式錯誤");
                }

                Integer empid = null;
                try {
                    empid = Integer.valueOf(req.getParameter("empid").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("員工編號格式錯誤");
                    empid = 0;
                }
                String coachTime = req.getParameter("coachTime");
//				String backcoachTime = "";
                String covercoachTime = "222222222222222222222222";

                CoachClassOrderVO coachClassOrderVO = new CoachClassOrderVO();
                coachClassOrderVO.setOrderID(orderid);
                coachClassOrderVO.setEmpID(empid);
                coachClassOrderVO.setMemID(memid);
                coachClassOrderVO.setClassTime(classTime);
                coachClassOrderVO.setOrderstatus(0);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("coachClassOrderVO", coachClassOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/back-end/coachclassorder/updateCoachClassOrder.jsp");
                    failureView.forward(req, res);
                    return; // �{�����_
                }
                /*************************** * 2.�}�l�ק��� *****************************************/
                coachClassOrderVO = coachSvc.updateEmp(orderid, empid, memid, classTime, orderstatus, covercoachTime);

                /***************************
                 * 3.�ק粒��,�ǳ����(Send the Success view)
                 *************/
                req.setAttribute("coachClassOrderVO", coachClassOrderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
                String url = "/back-end/coachclassorder/listOneCoachClassOrder.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
                successView.forward(req, res);

            }
        }

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************
             * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
             *************************/

            Integer empid = Integer.valueOf(req.getParameter("empID").trim());

            Integer memid = Integer.valueOf(req.getParameter("memid").trim());

            java.sql.Date classDate = java.sql.Date.valueOf(req.getParameter("coachDate").trim());

//			String 

            Integer orderstatus = Integer.valueOf(req.getParameter("orderstatus").trim());

            String[] coachTime = req.getParameterValues("coachTime");
            String coachclassperiod = "";
            String updatecoachtime = "";
            if (coachTime == null) {
                errorMsgs.add("請勾選你的時段");
            } else {
                aa:
                for (int j = 0; j < 24; j++) {
                    String ab = String.valueOf(j);

                    for (int i = 0; i < coachTime.length; i++) {
                        if (coachTime[i].equals(ab)) {
                            coachclassperiod += "0";
                            continue aa;
                        }

                    }
                    if (j == coachclassperiod.length()) {
                        coachclassperiod += "2";
                    }
                }

            }

            CoachClassOrderVO coachClassOrderVO = new CoachClassOrderVO();
            CoachTimeVO coachTimeVO = new CoachTimeVO();
            CoachTimeVO coachTimeVO2 = new CoachTimeVO();
            coachClassOrderVO.setCoachclassperiod(coachclassperiod);
            coachClassOrderVO.setEmpID(empid);
            coachClassOrderVO.setMemID(memid);
            coachClassOrderVO.setClassTime(classDate);
            coachClassOrderVO.setOrderstatus(1);

            // Send the use back to the form, if there were errors

//			coachClassOrderVO = coachSvc.updateEmp(orderid,empid);
            coachTimeVO2 = coachTimeService.getCoachTimeByCoachDate(empid, classDate);
            coachTimeVO.setEmpID(empid);
            coachTimeVO.setCoachDate(classDate);

            /***************************
             * 2.�}�l�s�W���
             ***************************************/

            List<CoachClassOrderVO> list = coachSvc.getAllCoachPeriodByEmpAndClassTime(empid, classDate);
            if (list.isEmpty()) {
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("coachTimeVO", coachTimeVO);
                    req.setAttribute("coachTimeVO2", coachTimeVO2);
                    req.setAttribute("coachClassOrderVO", coachClassOrderVO);
                    RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/addCoachClassOrder.jsp");
                    failureView.forward(req, res);
                    return;
                }
                coachClassOrderVO = coachSvc.addCoach(empid, memid, classDate, orderstatus, coachclassperiod);

                for (CoachClassOrderVO cco : list) {
                    for (int i = 0; i < cco.getCoachclassperiod().length(); i++) {
                        if (cco.getCoachclassperiod().charAt(i) == '0') {
                            updatecoachtime = coachTimeVO2.getCoachTime().substring(0, i) + '2' + coachTimeVO2.getCoachTime().substring((i + 1));
                            coachTimeVO2.setCoachTime(updatecoachtime);
                            if (cco.getCoachclassperiod().equals(coachclassperiod)) {
                                errorMsgs.add("已經被預約過了");
                                if (!errorMsgs.isEmpty()) {
                                    req.setAttribute("coachTimeVO", coachTimeVO);
                                    req.setAttribute("coachTimeVO2", coachTimeVO2);
                                    req.setAttribute("coachClassOrderVO", coachClassOrderVO);
                                    RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/addCoachClassOrder.jsp");
                                    failureView.forward(req, res);
                                    return;
                                }
                            }
                        }
                    }
                }
            } else {

                for (CoachClassOrderVO cco : list) {
                    for (int i = 0; i < cco.getCoachclassperiod().length(); i++) {
                        if (cco.getCoachclassperiod().charAt(i) == '0') {
                            updatecoachtime = coachTimeVO2.getCoachTime().substring(0, i) + '2' + coachTimeVO2.getCoachTime().substring((i + 1));
                            coachTimeVO2.setCoachTime(updatecoachtime);
                            if (cco.getCoachclassperiod().equals(coachclassperiod)) {
                                errorMsgs.add("已經被預約過了");
                                if (!errorMsgs.isEmpty()) {
                                    req.setAttribute("coachTimeVO", coachTimeVO);
                                    req.setAttribute("coachTimeVO2", coachTimeVO2);
                                    req.setAttribute("coachClassOrderVO", coachClassOrderVO);
                                    RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/addCoachClassOrder.jsp");
                                    failureView.forward(req, res);
                                    return;
                                }
                            }
                        }
                    }
                }
                coachClassOrderVO = coachSvc.addCoach(empid, memid, classDate, orderstatus, coachclassperiod);
            }
            if (!errorMsgs.isEmpty()) {

                req.setAttribute("coachTimeVO", coachTimeVO);
                req.setAttribute("coachTimeVO2", coachTimeVO2);
                req.setAttribute("coachClassOrderVO", coachClassOrderVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/addCoachClassOrder.jsp");
                failureView.forward(req, res);
                return;
            }
            /*************************** * 3.�s�W����,�ǳ����(Send the Success view)***********/
            String url = "/front-end/coach/coachList.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
            successView.forward(req, res);
        }

        if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************
             * 1.�����ШD�Ѽ�
             ***************************************/
            Integer orderid = Integer.valueOf(req.getParameter("orderid"));

            /***************************
             * 2.�}�l�R�����
             ***************************************/
            coachSvc.deleteEmp(orderid);

            /***************************
             * 3.�R������,�ǳ����(Send the Success view)
             ***********/
            String url = "/back-end/coachclassorder/listAllCoachClassOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
            successView.forward(req, res);
        }
    }
}
