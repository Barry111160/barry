package com.musclebeach.roomOrder.controller;


import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.room.model.RoomService;
import com.musclebeach.room.model.RoomVO;
import com.musclebeach.roomOrder.model.RoomOrderService;
import com.musclebeach.roomTime.model.RoomTimeService;
import org.springframework.context.ApplicationContext;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/roomOrder.do")
public class RoomOrderServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final RoomOrderService roomOrderService = context.getBean(RoomOrderService.class);
    private final RoomService roomService = context.getBean(RoomService.class);
    private final RoomTimeService roomTimeService = context.getBean(RoomTimeService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("pay_room_order".equals(action)) { // 預約付款 from room_order.jsp
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));
            RoomVO roomVO = roomService.getOneRoom(roomID);
            req.setAttribute("roomVO", roomVO);

            // borrowDate
            java.sql.Date borrowDate = java.sql.Date.valueOf(req.getParameter("borrowDate").trim());
            req.setAttribute("borrowDate", borrowDate);
            // borrowTime
            Integer borrowTime = Integer.valueOf(req.getParameter("borrowTime"));
            req.setAttribute("borrowTime", borrowTime);

            String url = "/front-end/creditCard/payForRoomOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("cancelApply".equals(action)) { // 會員取消 from room_order_info.jsp
            Integer orderID = Integer.valueOf(req.getParameter("orderID"));
            roomOrderService.cancelApplication(orderID);
        }

        if ("confirm".equals(action)) { // 員工確認訂單from room_order_pending.jsp
            Integer orderID = Integer.valueOf(req.getParameter("orderID"));
            Integer employeeID = Integer.valueOf(req.getParameter("employeeID"));
            roomOrderService.confirmRoomOrder(orderID, employeeID);
        }

        if ("cancel".equals(action)) { // 員工確認取消 from from room_order_pending.jsp
            Integer orderID = Integer.valueOf(req.getParameter("orderId"));
            Integer roomID = Integer.valueOf(req.getParameter("roomID"));
            java.sql.Date borrowDate = java.sql.Date.valueOf(req.getParameter("borrowDate").trim());
            Integer borrowTime = Integer.valueOf(req.getParameter("borrowTime"));
            Integer employeeID = Integer.valueOf(req.getParameter("employeeID"));
            /*****************************修改訂單狀態*****************************/
            roomOrderService.cancelRoomOrder(orderID, employeeID);
            /*****************************修改可預約時間****************************/
            String roomTime = roomTimeService.findByBorrowDate(roomID, borrowDate);
            StringBuilder stringBuilder = new StringBuilder(roomTime);
            switch (borrowTime) {
                case 0: // 取消上午
                    stringBuilder.setCharAt(0, '0');
                    break;
                case 1: // 取消下午
                    stringBuilder.setCharAt(1, '0');
                    break;
                case 2: // 取消晚上
                    stringBuilder.setCharAt(2, '0');
                    break;
                default:
                    break;
            }
            roomTime = stringBuilder.toString();
            roomTimeService.updateByOrder(roomTime, roomID, borrowDate);
        }
    }
}

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

            //設定信中的主旨
            message.setSubject(subject);
            //設定信中的內容
            message.setText(messageText);

            Transport.send(message);
            System.out.println("傳送成功!");
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }

}