package com.musclebeach.classSchedule.controller;

import com.musclebeach.classSchedule.model.ClassScheduleService;
import com.musclebeach.classSchedule.model.ClassScheduleVO;
import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/back-end/course/classSchedule/classSchedule.do")
    public class ClassScheduleServlet extends HttpServlet {
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final ClassScheduleService classScheduleService = ctx.getBean(ClassScheduleService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("listClassSchedules_ByCompositeQuery".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);


            /***************************1.將輸入資料轉為Map**********************************/
            //採用Map<String,String[]> getParameterMap()的方法
            //注意:an immutable java.util.Map
            //Map<String, String[]> map = req.getParameterMap();
            HttpSession session = req.getSession();
            Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

            // 以下的 if 區塊只對第一次執行時有效
            if (req.getParameter("whichPage") == null) {
                //因為怕map資安洩漏所以不能儲存在session中故需要強制轉型為 HashMap
                Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
                session.setAttribute("map", map1);
                map = map1;
            }

            /***************************2.開始複合查詢***************************************/
            List<ClassScheduleVO> list = classScheduleService.getAll(map);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("listClassSchedules_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
            RequestDispatcher successView = req.getRequestDispatcher("/back-end/course/classSchedule/listClassSchedules_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String str = req.getParameter("timeID");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入時間編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/classSchedule/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            Integer timeID = null;
            try {
                timeID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("時間編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/classSchedule/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            ClassScheduleVO classScheduleVO = classScheduleService.getOne(timeID);
            if (classScheduleVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/classSchedule/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("classScheduleVO", classScheduleVO);
            String url = "/back-end/course/classSchedule/listOneClassSchedule.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("insert".equals(action)) { // 來自addClassSchedule.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

            Integer classID = Integer.valueOf(req.getParameter("classID"));
            if (classID == null || String.valueOf(classID).trim().length() == 0) {
                errorMsgs.add("課程編號請勿空白");
            }
            String startTimestr = req.getParameter("startTime");

            if (startTimestr == null || startTimestr.trim().length() == 0) {
                errorMsgs.add("起始時間: 請勿空白");
            }
            Timestamp startTime = Timestamp.valueOf(startTimestr);


            String endTimestr = req.getParameter("endTime");
            if (endTimestr == null || endTimestr.trim().length() == 0) {
                errorMsgs.add("起始時間: 請勿空白");
            }
            Timestamp endTime = Timestamp.valueOf(endTimestr);


            /***************************2.開始新增資料***************************************/
            classScheduleService.addClassSchedule(classID, startTime, endTime);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/back-end/course/classSchedule/listAllClassSchedule.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClassSchedule.jsp
            successView.forward(req, res);
        }

        if ("delete".equals(action)) { // 來自listAllClassSchedule.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數***************************************/
            Integer timeID = Integer.valueOf(req.getParameter("timeID"));

            /***************************2.開始刪除資料***************************************/
            classScheduleService.deleteClassSchedule(timeID);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/back-end/course/classSchedule/listAllClassSchedule.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
        if ("getOne_For_Update".equals(action)) { // 來自listAllTeamClass.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer timeID = Integer.valueOf(req.getParameter("timeID"));

            /***************************2.開始查詢資料****************************************/
            ClassScheduleVO classScheduleVO = classScheduleService.getOne(timeID);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("classScheduleVO", classScheduleVO);
            String url = "/back-end/course/classSchedule/update_classSchedule_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }


        if ("update".equals(action)) { // 來自update_classSchedule_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer timeID = Integer.valueOf(req.getParameter("timeID").trim());
            Integer classID = Integer.valueOf(req.getParameter("classID").trim());
            String startTimestr = req.getParameter("startTime");

            if (startTimestr == null || startTimestr.trim().length() == 0) {
                errorMsgs.add("起始時間: 請勿空白");
            }
            Timestamp startTime = Timestamp.valueOf(startTimestr);


            String endTimestr = req.getParameter("endTime");
            if (endTimestr == null || endTimestr.trim().length() == 0) {
                errorMsgs.add("起始時間: 請勿空白");
            }
            Timestamp endTime = Timestamp.valueOf(endTimestr);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/update_teamClass_input.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            ClassScheduleVO classScheduleVO = classScheduleService.updateClassSchedule(classID, startTime, endTime, timeID);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("classScheduleVO", classScheduleVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/course/classSchedule/listOneClassSchedule.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }
    }
}
