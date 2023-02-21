package com.musclebeach.creditCard.controller;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.creditCard.model.CreditCardService;
import com.musclebeach.roomOrder.model.RoomOrderService;
import com.musclebeach.roomOrder.model.RoomOrderVO;
import com.musclebeach.roomTime.model.RoomTimeService;
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

@WebServlet("/payRoomOrder")
public class PayRoomOrderServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final RoomOrderService roomOrderService = context.getBean(RoomOrderService.class);
    private final CreditCardService creditCardSvc = context.getBean(CreditCardService.class);
    private final RoomTimeService roomTimeService = context.getBean(RoomTimeService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("insert_room_order".equals(action)) { // from payForRoomOrder.jsp

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer memID = Integer.valueOf(req.getParameter("memID"));
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));
            java.sql.Date borrowDate = java.sql.Date.valueOf(req.getParameter("borrowDate"));
            Integer borrowTime = Integer.valueOf(req.getParameter("borrowTime"));
            Integer orderStatus = 1;

            String ccNumber = req.getParameter("ccNumber");
            String ccName = req.getParameter("ccName").trim();
            String ccTime = req.getParameter("ccTime").trim();
            String ccvc = req.getParameter("ccvc").trim();

            /***************************2.開始新增/修改資料*************************************/
            // 新增訂單
            RoomOrderVO roomOrderVO = roomOrderService.addOrder(memID, roomID, borrowDate, borrowTime, orderStatus);
            // 勾選儲存資訊(尚未判斷是否儲存過!!)
            if (req.getParameter("saveCard") != null) {
                creditCardSvc.addCard(memID, ccNumber, ccName, ccTime, ccvc);
            }
            // 修改可預約時間
            String roomTime = roomTimeService.findByBorrowDate(roomID, borrowDate);
            StringBuilder stringBuilder = new StringBuilder(roomTime);
            switch (borrowTime) {
                case 0: // 預約上午
                    stringBuilder.setCharAt(0, '1');
                    break;
                case 1: // 預約下午
                    stringBuilder.setCharAt(1, '1');
                    break;
                case 2: // 預約晚上
                    stringBuilder.setCharAt(2, '1');
                    break;
                default:
                    break;
            }
            roomTime = stringBuilder.toString();
            roomTimeService.updateByOrder(roomTime, roomID, borrowDate);
            /***************************3.執行完成,準備轉交(Send the Success view)*************/
            req.setAttribute("roomOrderVO", roomOrderVO);
            String url = "/front-end/room/room_order_paid.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

    }

}
