package com.musclebeach.articleType.controller;

import com.musclebeach.articleType.model.ArticleTypeService;
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

@WebServlet({"/back-end/article/articleType/articleType.do", "/front-end/article/articleType/articleType.do"})
public class ArticleTypeServlet extends HttpServlet {
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final ArticleTypeService articleTypeService = ctx.getBean(ArticleTypeService.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        super.doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("insert".equals(action)) {

            List<String> errorMsgs1 = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs1", errorMsgs1);

//		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/


            String typeName = req.getParameter("typeName");
            String typeNameReg = "^.{1,200}$";
            if (typeName == null || typeName.trim().length() == 0) {
                errorMsgs1.add("文章類別: 請勿空白");
            } else if (!typeName.trim().matches(typeNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs1.add("長度必需在1到200之間");
            }


//	    再次查詢單筆文章 把文章資料傳遞過去
            /***************************2.開始查詢資料*****************************************/
            System.out.println("123");
            articleTypeService.addArticleType(typeName);

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            String url = "/back-end/article/articleType/listAllArticleType.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
            successView.forward(req, res);
        }
    }
}
