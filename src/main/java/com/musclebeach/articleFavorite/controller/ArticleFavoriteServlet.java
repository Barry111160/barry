package com.musclebeach.articleFavorite.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.musclebeach.article.model.*;
import com.musclebeach.articleFavorite.model.*;
import com.musclebeach.articleLike.model.ArticleLikeService;
import com.musclebeach.articleLike.model.ArticleLikeVO;
import com.musclebeach.article.model.ArticleService;
import com.musclebeach.articleFavorite.model.ArticleFavoriteService;
import com.musclebeach.articleFavorite.model.ArticleFavoriteVO;
import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;



@WebServlet({"/back-end/article/articleFavorite.do", "/front-end/article/articleFavorite.do"})
public class ArticleFavoriteServlet extends HttpServlet {
	private final ApplicationContext ctx = ApplicationContextUtil.getContext();
	private final ArticleFavoriteService articleFavoriteService = ctx.getBean(ArticleFavoriteService.class);

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addArticle.jsp的請求 案讚

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			Integer memID = null;
			try {
				memID = Integer.valueOf(req.getParameter("memID").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("會員編號請填數字.");
			}

			Integer artID = null;
			try {
				artID = Integer.valueOf(req.getParameter("artID").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請選擇文章");
			}

			ArticleFavoriteVO articleFavoriteVO = new ArticleFavoriteVO();
			articleFavoriteVO.setArtID(artID);
			articleFavoriteVO.setMemID(memID);


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleFavoriteVO", articleFavoriteVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/

			articleFavoriteVO = articleFavoriteService.addArticleFavorite(artID, memID);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			return;


//			req.setAttribute("articleFavoriteVO", articleFavoriteVO);
//			String url = "/front-end/article/listOneArticle.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
//			successView.forward(req, res);

		}


		if ("delete".equals(action)) { // 來自addArticle.jsp的請求 取消讚

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			Integer memID = null;
			try {
				memID = Integer.valueOf(req.getParameter("memID").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("會員編號請填數字.");
			}

			Integer artID = null;
			try {
				artID = Integer.valueOf(req.getParameter("artID").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請選擇文章");
			}


			ArticleFavoriteVO articleFavoriteVO = new ArticleFavoriteVO();
			articleFavoriteVO.setArtID(artID);
			articleFavoriteVO.setMemID(memID);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleFavoriteVO", articleFavoriteVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始刪除資料 ***************************************/

			articleFavoriteService.deleteArtFavorite(artID, memID);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			return;
//			String url = "/front-end/article/listOneArticle.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
//			successView.forward(req, res);

		}


//		if ("getAll_For_Display_BY_MemID".equals(action)) { // 來自文章列表 list.jsp的請求 查類別的文章
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer memID = Integer.valueOf(req.getParameter("memID").trim());
//
//			/*************************** 2.開始查詢資料 *****************************************/

//			List<ArticleFavoriteVO> listByMemID = articleFavoriteService.getAllByMemID(memID);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("listByMemID", listByMemID); // 資料庫取出的empVO物件,存入req
//			String url = "/front-end/article/listAllArticleFavorite.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
//			successView.forward(req, res);
//
//
//		}
	}
}
