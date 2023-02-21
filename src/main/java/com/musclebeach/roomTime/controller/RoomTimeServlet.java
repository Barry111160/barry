package com.musclebeach.roomTime.controller;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.room.model.RoomService;
import com.musclebeach.room.model.RoomVO;
import com.musclebeach.roomTime.model.RoomTimeService;
import com.musclebeach.roomTime.model.RoomTimeVO;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

class DateAdd {
    public Timestamp addDays(Timestamp date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// w ww. j ava 2 s .co m
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return new Timestamp(cal.getTime().getTime());

    }
}

@WebServlet("/back-end/roomTime/roomTime.do")
public class RoomTimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final RoomService roomSvc = context.getBean(RoomService.class);
    private final RoomTimeService roomTimeSvc = context.getBean(RoomTimeService.class);

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
            String str = req.getParameter("timeID");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入場地可預約時間編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer timeID = null;
            try {
                timeID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            RoomTimeVO roomTimeVO = roomTimeSvc.getOneRoomeTime(timeID);
            if (roomTimeVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("roomTimeVO", roomTimeVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/room/listOneRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOneRoom_For_UpdateTime".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));

            /*************************** 2.開始查詢資料 ****************************************/

            RoomVO roomVO = roomSvc.getOneRoom(roomID);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("roomVO", roomVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/roomTime/updateRoomTime.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer timeID = Integer.valueOf(req.getParameter("timeID").trim());

            Integer roomID = Integer.valueOf(req.getParameter("roomID").trim());

            Integer borrowTime = Integer.valueOf(req.getParameter("borrowTime").trim());

            java.sql.Date roomDate = null;
            try {
                roomDate = java.sql.Date.valueOf(req.getParameter("borrowDate").trim());
            } catch (IllegalArgumentException e) {
                roomDate = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            RoomTimeVO roomTimeVO = roomTimeSvc.getOneByRoomAndDate(roomID, roomDate);
            String roomTime = roomTimeVO.getRoomTime();
            String newRoomTime = null;
            if (borrowTime == 0) {
                int index = 0;
                newRoomTime = '2' + roomTime.substring(index + 1);
            } else if (borrowTime == 1) {
                int index = 1;
                newRoomTime = roomTime.substring(0, index) + '2' + roomTime.substring(index + 1);
            } else if (borrowTime == 2) {
                int index = 2;
                newRoomTime = roomTime.substring(0, index) + '2';
            }

            /*************************** 2.開始修改資料 *****************************************/
            roomTimeSvc.updateRoomTime(roomTimeVO.getTimeID(), roomID, roomDate, newRoomTime);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("roomTimeVO", roomTimeVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/roomTime/listAllRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));
            Date startDate = Date.valueOf(req.getParameter("start_time"));
            java.sql.Timestamp start_time = java.sql.Timestamp
                    .valueOf(req.getParameter("start_time") + " " + "00:00:00");
            Date endDate = Date.valueOf(req.getParameter("end_time"));
//			java.sql.Timestamp end_time = java.sql.Timestamp.valueOf(req.getParameter("end_time") + " " + "00:00:00");

            Integer all = roomTimeSvc.getDates(startDate, endDate);
            DateAdd dates = new DateAdd();

            for (int i = 0; i < all; i++) {
                long millis2 = start_time.getTime();
                java.sql.Date date = new java.sql.Date(millis2);
                roomTimeSvc.addRoomTime(roomID, date, "000");
                start_time = dates.addDays(start_time, 1);
            }
            String url = "/back-end/roomTime/listAllRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);

        }

        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer timeID = Integer.valueOf(req.getParameter("timeID"));

            /*************************** 2.開始刪除資料 ***************************************/
            roomTimeSvc.deleteRoomTime(timeID);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/room/listAllRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
    }
}