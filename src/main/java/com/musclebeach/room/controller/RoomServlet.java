package com.musclebeach.room.controller;


import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.mem.model.MemVO;
import com.musclebeach.room.model.RoomService;
import com.musclebeach.room.model.RoomVO;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;


@MultipartConfig
@WebServlet({"/back-end/room/room.do", "/front-end/room/room.do"})
public class RoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final RoomService roomSvc = context.getBean(RoomService.class);

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
            String str = req.getParameter("roomID");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入場地編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer roomID = null;
            try {
                roomID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("員工編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/

            RoomVO roomVO = roomSvc.getOneRoom(roomID);
            if (roomVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("roomVO", roomVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/room/listOneRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

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
            String url = "/back-end/room/update_room_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer roomID = Integer.valueOf(req.getParameter("roomID").trim());

            String roomName = req.getParameter("roomName").trim();
            String roomNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (roomName == null || roomName.trim().length() == 0) {
                errorMsgs.add("場地名稱: 請勿空白");
            } else if (!roomName.trim().matches(roomNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String roomAddress = req.getParameter("roomAddress").trim();
            if (roomAddress == null || roomAddress.trim().length() == 0) {
                errorMsgs.add("地址: 請勿空白");
            }

            String roomContent = req.getParameter("roomContent").trim();
            if (roomContent == null || roomContent.trim().length() == 0) {
                errorMsgs.add("場地內容: 請勿空白");
            }

            Integer roomStatus = Integer.valueOf(req.getParameter("roomStatus").trim());

            Integer roomPrice = null;
            try {
                roomPrice = Integer.valueOf(req.getParameter("roomPrice").trim());
            } catch (NumberFormatException e) {
                roomPrice = 0;
                errorMsgs.add("場地價格請填數字.");
            }

            Part part = req.getPart("roomImg"); // 來自於上面的form表單
            InputStream in = part.getInputStream();
            byte[] b = new byte[in.available()];
            if (b.length == 0) {

                RoomVO roomVO = roomSvc.getOneRoom(roomID);
                b = roomVO.getRoomImg();
            } else {
                in.read(b);
                in.close();
            }

            RoomVO roomVO = new RoomVO();
            roomVO.setRoomID(roomID);
            roomVO.setRoomName(roomName);
            roomVO.setRoomAddress(roomAddress);
            roomVO.setRoomContent(roomContent);
            roomVO.setRoomPrice(roomPrice);
            roomVO.setRoomStatus(roomStatus);
            roomVO.setRoomImg(b);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/update_room_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/

            roomVO = roomSvc.updateRoom(roomID, roomName, roomAddress, roomContent, roomPrice, roomStatus, b);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("roomVO", roomVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/room/listAllRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

            String roomName = req.getParameter("roomName").trim();
            String roomNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (roomName == null || roomName.trim().length() == 0) {
                errorMsgs.add("場地名稱: 請勿空白");
            } else if (!roomName.trim().matches(roomNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String roomAddress = req.getParameter("roomAddress").trim();
            if (roomAddress == null || roomAddress.trim().length() == 0) {
                errorMsgs.add("地址: 請勿空白");
            }

            String roomContent = req.getParameter("roomContent").trim();
            if (roomContent == null || roomContent.trim().length() == 0) {
                errorMsgs.add("場地內容: 請勿空白");
            }

            Integer roomStatus = Integer.valueOf(req.getParameter("roomStatus").trim());

            Integer roomPrice = null;
            try {
                roomPrice = Integer.valueOf(req.getParameter("roomPrice").trim());
            } catch (NumberFormatException e) {
                roomPrice = 0;
                errorMsgs.add("場地價格請填數字.");
            }

            Part part = req.getPart("roomImg"); // 來自於上面的form表單
            InputStream in = part.getInputStream();
            byte[] b = new byte[in.available()];
            in.read(b);
            in.close();

            RoomVO roomVO = new RoomVO();
            roomVO.setRoomName(roomName);
            roomVO.setRoomAddress(roomAddress);
            roomVO.setRoomContent(roomContent);
            roomVO.setRoomPrice(roomPrice);
            roomVO.setRoomStatus(roomStatus);
            roomVO.setRoomImg(b);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/

            roomVO = roomSvc.addRoom(roomName, roomAddress, roomContent, roomPrice, roomStatus, b);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("roomVO", roomVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/room/listAllRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }


        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));

            /*************************** 2.開始刪除資料 ***************************************/

            roomSvc.deleteRoom(roomID);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/room/listAllRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }

        if ("getImg".equals(action)) {
            ServletOutputStream out = res.getOutputStream();
            String roomID = req.getParameter("roomID");

            RoomVO roomVO = roomSvc.getOneRoom(Integer.parseInt(roomID));
            res.setContentType("image/jpg");
            res.setContentLength(roomVO.getRoomImg().length);
            out.write(roomVO.getRoomImg());
            out.close();
        }

        if ("roomDetail".equals(action)) {
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));


            RoomVO roomVO = roomSvc.getOneRoom(roomID);

            req.setAttribute("roomVO", roomVO);
            String url = "/front-end/room/listOneRoom.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("booking".equals(action)) {
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));

            MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");

            if (memVO == null) { // 未登入
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            if (memVO.getMemAccess() == 0) { // 一般會員
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/creditCard/creditCardByRoom.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

//			Integer roomID = Integer.valueOf(req.getParameter("roomID"));


            RoomVO roomVO = roomSvc.getOneRoom(roomID);

            req.setAttribute("roomVO", roomVO);
            String url = "/front-end/room/room_order.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

    }

}
