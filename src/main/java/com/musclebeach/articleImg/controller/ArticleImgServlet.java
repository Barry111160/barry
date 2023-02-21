package com.musclebeach.articleImg.controller;

import com.musclebeach.articleImg.model.ArticleImgService;
import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
@WebServlet({"/back-end/article/articleImg.do", "/front-end/article/articleImg.do"})
public class ArticleImgServlet extends HttpServlet {

	private final ApplicationContext ctx = ApplicationContextUtil.getContext();
	private final ArticleImgService articleImgService = ctx.getBean(ArticleImgService.class);

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			Integer artID = Integer.valueOf(req.getParameter("artID").trim());
			// 照片
			List<byte[]> artImgs = new ArrayList<byte[]>();
			Collection<Part> parts = req.getParts();

			for (Part part : parts) {
				if (part.getName().equals("artImg") && part.getSize() != 0) {
					InputStream in = part.getInputStream();
					byte[] imgData = new byte[in.available()];
					in.read(imgData);
					in.close();
					artImgs.add(imgData);
				}
			}
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
			/*************************** 2.開始新增資料 ***************************************/

			articleImgService.addWithArticleImgs(artID, artImgs);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/front-end/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
			successView.forward(req, res);
		
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer imgID = Integer.valueOf(req.getParameter("imgID"));
				/***************************2.開始刪除資料***************************************/

			articleImgService.deleteArticleImg(imgID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				return;
//				String url = "/front-end/article/listAllArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
		}
	}
}
