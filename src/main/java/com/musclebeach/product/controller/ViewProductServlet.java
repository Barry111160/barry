package com.musclebeach.product.controller;


import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.product.model.entity.Product;
import com.musclebeach.product.model.service.ProductService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/front-end/product/shopType", "/front-end/shop/search"})
public class ViewProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final ProductService productServiceFront = context.getBean(ProductService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("getType_Display".equals(action)) { // 來自shop.jsp的請求
            Integer typeID = Integer.parseInt(req.getParameter("typeID"));
            List<Product> listByTypeID = productServiceFront.findSameCategoryProduct(typeID);

            req.setAttribute("listByTypeID", listByTypeID);
            RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/shopType.jsp");
            successView.forward(req, res);
        }
        if ("getSearch_Display".equals(action)) { // 來自shop.jsp的請求

            String keyword = req.getParameter("keyword");
            List<Product> list = productServiceFront.findSpecifiedProduct(keyword);
            System.out.println(list.size());
            req.setAttribute("keywordList", list);
            RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/shopSearch.jsp");
            successView.forward(req, res);
        }

    }
}
