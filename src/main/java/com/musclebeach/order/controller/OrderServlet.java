package com.musclebeach.order.controller;


import com.musclebeach.cart.entity.CartItem;
import com.musclebeach.cart.entity.CartProduct;
import com.musclebeach.cart.service.CartProductService;
import com.musclebeach.cart.service.CartService;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.mem.model.MemVO;
import com.musclebeach.order.model.entity.OrderDetail;
import com.musclebeach.order.model.entity.OrderMaster;
import com.musclebeach.order.model.service.DetailService;
import com.musclebeach.order.model.service.MasterService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/back-end/order/", "/front-end/order/"})
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final DetailService detailService = context.getBean(DetailService.class);
    private final MasterService masterService = context.getBean(MasterService.class);
    private final CartService cartService = context.getBean(CartService.class);
    private final CartProductService cartProductService = context.getBean(CartProductService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        // 結帳新增訂單及訂單明細
        if ("insertOrder".equals(action)) { // 來自checkout.jsp的請求

            /*********************** 1.接收請求參數  *************************/
//            Integer memID = Integer.valueOf(req.getParameter("memID"));
//            Integer totalPrice = Integer.valueOf(req.getParameter("totalPrice"));
            String orderRecName = req.getParameter("orderRecName");
            String orderRecPhone = req.getParameter("orderRecPhone");
            String orderAddress = req.getParameter("orderAddress");


            /*************************** 2.開始新增資料 ***************************************/
            MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
            Integer memID = memVO.getMemID();
            List<CartItem> allInCartByMemID = cartService.getAllInCartByMemID(memID);
            List<CartProduct> cartProductList = cartProductService.getCartProduct(allInCartByMemID);
            int totalPrice = 0;
            for (CartProduct cartProduct : cartProductList) {
                totalPrice += (cartProduct.getProPrice() * cartProduct.getCount());
            }
            Integer orderID = masterService.addMaster(memID, totalPrice, orderRecName, orderRecPhone, orderAddress, 0);
            for (CartProduct cartProduct : cartProductList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(orderID);
                orderDetail.setProID(cartProduct.getProID());
                orderDetail.setDetQty(cartProduct.getCount());
                orderDetail.setDetPrice(cartProduct.getProPrice() * cartProduct.getCount());
                detailService.add(orderDetail);
            }
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            req.getSession().removeAttribute("cartList");
            cartService.deleteAllInCart(memID);
            String url = "/front-end/product/memOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交shop.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) {

            /***************************1.接收請求參數****************************************/
            Integer orderID = Integer.valueOf(req.getParameter("orderID"));
            /***************************2.開始查詢資料****************************************/
            OrderMaster orderVO = masterService.getOneMaster(orderID);
            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("orderVO", orderVO);         // 資料庫取出的orderVO物件,存入req

            String url = "/back-end/order/updateOrderStatus.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateOrderStatus.jsp
            successView.forward(req, res);
            return;
        }

        if ("changeStatus".equals(action)) {

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer orderID = Integer.valueOf(req.getParameter("orderID").trim());

            Integer orderStatus = Integer.valueOf(req.getParameter("orderStatus").trim());

            OrderMaster orderVO = new OrderMaster();
            orderVO.setOrderID(orderID);
            orderVO.setOrderStatus(orderStatus);

            /***************************2.開始修改資料*****************************************/

            orderVO = masterService.changeStatus(orderID, orderStatus);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("orderVO", orderVO);
            String url = "/back-end/order/listAllOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("getOne_For_Detail".equals(action)) {

            /***************************1.接收請求參數****************************************/
            Integer orderID = Integer.valueOf(req.getParameter("orderID"));
            /***************************2.開始查詢資料****************************************/
            List<OrderDetail> detailVO = masterService.getOneDetail(orderID);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("detailVO", detailVO);         // 資料庫取出的detailVO物件,存入req
            String url = "/back-end/order/listAllOrderDetail.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listAllOrderDetail.jsp
            successView.forward(req, res);
        }
    }
}
