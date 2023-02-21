package com.musclebeach.articleLike.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.musclebeach.articleFavorite.model.ArticleFavoriteService;

import com.musclebeach.articleLike.model.ArticleLikeService;
import com.musclebeach.articleLike.model.ArticleLikeVO;

import com.musclebeach.articleReport.model.ArticleReportService;
import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

@WebServlet({"/back-end/article/articleLike.do", "/front-end/article/articleLike.do"})
public class ArticleLikeServlet extends HttpServlet {
	private final ApplicationContext ctx = ApplicationContextUtil.getContext();
	private final ArticleLikeService articleLikeService = ctx.getBean(ArticleLikeService.class);

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


			ArticleLikeVO artLikeVO = new ArticleLikeVO();
			artLikeVO.setArtID(artID);
			artLikeVO.setMemID(memID);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artLikeVO", artLikeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/

			artLikeVO = articleLikeService.addArtLike(artID, memID);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			
			List<ArticleLikeVO> list = articleLikeService.getAllArtLike(artID);
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().print(list.size());
			return;
			
//			req.setAttribute("artLikeVO", artLikeVO);
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

			ArticleLikeVO artLikeVO = new ArticleLikeVO();
			artLikeVO.setArtID(artID);
			artLikeVO.setMemID(memID);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artLikeVO", artLikeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始刪除資料 ***************************************/

			articleLikeService.deleteArtLike(artID, memID);

			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			List<ArticleLikeVO> list = articleLikeService.getAllArtLike(artID);
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().print(list.size());
			return;
			
			
//			String url = "/front-end/article/listOneArticle.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
//			successView.forward(req, res);

		}
		
		
	}
}
