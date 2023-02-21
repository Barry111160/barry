package com.musclebeach.teamClass.controller;


import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.course.service.ClassTypeService;
import com.musclebeach.teamClass.model.TeamClassService;
import com.musclebeach.teamClass.model.TeamClassVO;
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

@WebServlet("/back-end/course/teamClass/teamClass.do")
public class TeamClassServlet extends HttpServlet {
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    TeamClassService teamClassService = ctx.getBean(TeamClassService.class);


    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");


        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String str = req.getParameter("classID");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入課程編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            Integer classID = null;
            try {
                classID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("課程編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            TeamClassVO teamClassVO = teamClassService.getOne(classID);
            if (teamClassVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("teamClassVO", teamClassVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/course/teamClass/listOneTeamClass.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }


        if ("getOne_For_Update".equals(action)) { // 來自listAllTeamClass.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer classID = Integer.valueOf(req.getParameter("classID"));

            /***************************2.開始查詢資料****************************************/
            TeamClassVO teamClassVO = teamClassService.getOne(classID);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("teamClassVO", teamClassVO);         // 資料庫取出的teamClassVO物件,存入req
            String url = "/back-end/course/teamClass/update_teamClass_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_teamClass_input.jsp
            successView.forward(req, res);
        }


        if ("update".equals(action)) { // 來自update_teamClass_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer classID = Integer.valueOf(req.getParameter("classID").trim());
            Integer empID = Integer.valueOf(req.getParameter("empID").trim());
            Integer typeID = Integer.valueOf(req.getParameter("typeID").trim());
            String className = req.getParameter("className");
            String classNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (className == null || className.trim().length() == 0) {
                errorMsgs.add("課程名稱: 請勿空白");
            } else if (!className.trim().matches(classNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("課程名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
            Integer peopleMax = Integer.valueOf(req.getParameter("peopleMax").trim());
            String classContent = req.getParameter("classContent").trim();
            if (classContent == null || classContent.trim().length() == 0) {
                errorMsgs.add("人數上限請勿空白");
            }


            Integer classStatus = Integer.valueOf(req.getParameter("classStatus").trim());

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/update_teamClass_input.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            TeamClassVO teamClassVO = teamClassService.updateTeamClass(classID, empID, typeID, className, peopleMax, classContent, classStatus);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("teamClassVO", teamClassVO);
            String url = "/back-end/course/teamClass/listOneTeamClass.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addTeamClass.jsp的請求  

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            Integer empID = Integer.valueOf(req.getParameter("empID").trim());
            if (empID == null || String.valueOf(empID).length() == 0) {
                errorMsgs.add("員工編號請勿空白");
            }
            Integer typeID = Integer.valueOf(req.getParameter("typeID").trim());
            if (typeID == null || String.valueOf(typeID).trim().length() == 0) {
                errorMsgs.add("職位請勿空白");
            }
            String className = req.getParameter("className");
            String classNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (className == null || className.trim().length() == 0) {
                errorMsgs.add("課程名稱: 請勿空白");
            } else if (!className.trim().matches(classNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("課程名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
            Integer peopleMax = Integer.valueOf(req.getParameter("peopleMax").trim());
            String classContent = req.getParameter("classContent").trim();
            if (classContent == null || classContent.trim().length() == 0) {
                errorMsgs.add("課程內容請勿空白");
            }


            Integer classStatus = Integer.valueOf(req.getParameter("classStatus").trim());

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/addTeamClass.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.開始新增資料***************************************/
            teamClassService.addTeamClass(empID, typeID, className, peopleMax, classContent, classStatus);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/back-end/course/teamClass/listAllTeamClass.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }


        if ("delete".equals(action)) { // 來自listAllTeamClass.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數***************************************/
            Integer classID = Integer.valueOf(req.getParameter("classID"));

            /***************************2.開始刪除資料***************************************/
            teamClassService.deleteTeamClass(classID);
            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/back-end/course/teamClass/listAllTeamClass.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }

        if ("getAllClass_For_OneType".equals(action)) { // 來自select_page.jsp的請求

        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
        String str = req.getParameter("typeID");
        if (str == null || (str.trim()).length() == 0) {
            errorMsgs.add("請輸入課程類別");
        }
        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
            RequestDispatcher failureView = req
                    .getRequestDispatcher("/back-end/course/teamClass/select_page.jsp");
            failureView.forward(req, res);
            return;//程式中斷
        }

            Integer typeID = null;
            try {
                typeID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("類別編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            TeamClassVO teamClassVO = teamClassService.getType(typeID);
            if (teamClassVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/teamClass/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("teamClassVO", teamClassVO);
            String url = "/back-end/course/teamClass/listAllTeamClassByOneType.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        }
    }



