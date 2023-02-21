package com.musclebeach.articleMessage.controller;

import com.musclebeach.article.model.ArticleService;
import com.musclebeach.article.model.ArticleVO;
import com.musclebeach.articleMessage.model.ArticleMessageService;
import com.musclebeach.articleMessage.model.ArticleMessageVO;
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

@WebServlet({"/back-end/article/articleMessage.do", "/front-end/article/articleMessage.do"})
public class ArticleMessageServlet extends HttpServlet {
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final ArticleMessageService articleMessageService = ctx.getBean(ArticleMessageService.class);
    private final ArticleService articleService = ctx.getBean(ArticleService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOneMessage_For_Article".equals(action)) { // 來自文章列表 要查全部留言的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer artID = Integer.valueOf(req.getParameter("artID").trim());

            /***************************2.開始查詢資料*****************************************/
            List<ArticleMessageVO> list = articleMessageService.getAllByArtID(artID);

            System.out.println(artID);
            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
            String url = "/front-end/article/article_all.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
            successView.forward(req, res);
        }


        if ("insert".equals(action)) { // 來自listOneArticle.jsp的請求 新增留言

            List<String> errorMsgs2 = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs2", errorMsgs2);

//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

            Integer artID = Integer.valueOf(req.getParameter("artID").trim());
            Integer memID = null;
            try {
                memID = Integer.valueOf(req.getParameter("memID").trim());
            } catch (NumberFormatException e) {
                errorMsgs2.add("會員編號請填數字.");
            }

            String msgContent = req.getParameter("msgContent");
            String msgContentReg = "^.{1,200}$";
            if (msgContent == null || msgContent.trim().length() == 0) {
                errorMsgs2.add("留言: 請勿空白");
            } else if (!msgContent.trim().matches(msgContentReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs2.add("長度必需在1到200之間");
            }

            ArticleMessageVO articleMessageVO = new ArticleMessageVO();
            articleMessageVO.setMemID(memID);
            articleMessageVO.setMsgContent(msgContent);


//			    再次查詢單筆文章 把文章資料傳遞過去
            /***************************2.開始查詢資料*****************************************/
            ArticleVO articleVO = articleService.getOneArticleVO(artID);

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
//			    再次查詢單筆文章 把文章資料傳遞過去	


            // Send the use back to the form, if there were errors
            if (!errorMsgs2.isEmpty()) {
                req.setAttribute("articleMessageVO", articleMessageVO); // 含有輸入格式錯誤的articleMessageVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/article/listOneArticle.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始新增資料***************************************/
            articleMessageVO = articleMessageService.addArticleMessage(artID, memID, msgContent);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/

            String url = "/front-end/article/listOneArticle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
            successView.forward(req, res);

        }


    }
}
