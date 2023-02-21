package com.musclebeach.roomTime.controller;


import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.roomTime.model.RoomTimeService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getRoomTime")
public class GetRoomTimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final RoomTimeService roomTimeService = context.getBean(RoomTimeService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //好的做法

        Integer roomID = Integer.valueOf(req.getParameter("roomID"));
        java.sql.Date borrowDate = java.sql.Date.valueOf(req.getParameter("borrowDate"));
        String roomTime = roomTimeService.findByBorrowDate(roomID, borrowDate);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.println(roomTime);

        //被噴爆的做法!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//		Integer roomID = Integer.valueOf(req.getParameter("roomID"));
//		java.sql.Date borrowDate = java.sql.Date.valueOf(req.getParameter("borrowDate"));
//		RoomTimeService roomTimeSvc = new RoomTimeService();
//		RoomTimeVO roomTimeVO = roomTimeSvc.getOneByRoomAndDate(roomID, borrowDate);
//		if(roomTimeVO == null) {
//			roomTimeSvc.addRoomTime(roomID, borrowDate, "000");
//		}
//		roomTimeVO = roomTimeSvc.getOneByRoomAndDate(roomID, borrowDate);
//		String roomTime = roomTimeVO.getRoomTime();
//		res.setCharacterEncoding("UTF-8");
//		PrintWriter writer = res.getWriter();
//		writer.println(roomTime);
    }
}
