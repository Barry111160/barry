package com.musclebeach.absentMember.controller;


import com.musclebeach.absentMember.model.AbsentMemberService;
import com.musclebeach.absentMember.model.AbsentMemberVO;
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
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/back-end/absentMember/absentMember.do")
public class AbsentMemberServlet extends HttpServlet {
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final AbsentMemberService absentMemberService = ctx.getBean(AbsentMemberService.class);
    private final ClassScheduleService classScheduleService = ctx.getBean(ClassScheduleService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("insertandgetCount".equals(action)) { // 來自listClassSchedules_ByCompositeQuery.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            Integer timeID = Integer.valueOf(req.getParameter("timeID"));
            if (timeID == null || String.valueOf(timeID).length() == 0) {
                errorMsgs.add("時間編號請勿空白");
            }
            Integer memID = Integer.valueOf(req.getParameter("memID"));
            if (memID == null || String.valueOf(memID).trim().length() == 0) {
                errorMsgs.add("會員編號請勿空白");
            }
            Integer classID = Integer.valueOf(req.getParameter("classID"));
            if (memID == null || String.valueOf(classID).trim().length() == 0) {
                errorMsgs.add("課程編號請勿空白");
            }


            /***************************2.開始新增資料***************************************/
            absentMemberService.addAbsentMember(timeID, memID);
            AbsentMemberVO absentMemberVO = absentMemberService.getAbsentCount(classID, memID);
            ClassScheduleVO classScheduleVO = classScheduleService.getClassCount(classID);
            double a = absentMemberVO.getCount();
            double b = classScheduleVO.getClassCount();
            if ((a / b) >= (1 / 3.0)) {
                absentMemberService.suspendMembership(memID);
                errorMsgs.add("會員編號" + memID + "號會員已被停權");
            }


            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/course/absentMember/listAllAbsentMember.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }
            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/back-end/course/absentMember/listAllAbsentMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

    }

}


