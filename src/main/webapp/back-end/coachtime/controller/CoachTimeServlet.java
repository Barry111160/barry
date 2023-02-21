package com.coachtime.controller;

import java.awt.Choice;
import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import com.coachclassorder.model.CoachClassOrderVO;
import com.coachtime.model.CoachTimeService;
import com.coachtime.model.CoachTimeVO;
import com.emp.model.EmpVO;
import com.coachclassorder.model.CoachClassOrderService;

public class CoachTimeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自selectCoachTimePage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("empid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入教練編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer empid = null;
			try {
				empid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("教練編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CoachTimeService coachSvc = new CoachTimeService();
			CoachTimeVO coachTimeVO = coachSvc.getOneEmp(empid);
			if (empid == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("coachTimeVO", coachTimeVO); // 資料庫取出的VO物件,存入req
			String url = "/back-end/coachtime/listOneCoachTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllCoachTime.jsp
			successView.forward(req, res);
		}

		if ("getAll_For_DisplaybyEMP".equals(action)) { // 來自selectCoachTimePage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("empid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入教練編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer empid = null;
			try {
				empid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("教練編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CoachTimeService coachSvc = new CoachTimeService();
			
			List<CoachTimeVO> coachTimeVO2 = coachSvc.getAllByEmpID(empid);
			if (empid == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("coachTimeVO2", coachTimeVO2); // 資料庫取出的VO物件,存入req
			String url = "/back-end/coachtime/listAllCoachTimebyEMP.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllCoachTime.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("getOneEmpid".equals(action)) { // 來自selectCoachTimePage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("empid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入教練編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer empid = null;
			try {
				empid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("教練編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CoachTimeService coachSvc = new CoachTimeService();
			EmpVO empVO = new EmpVO();
			empVO.setEmpID(empid);
			if (empid == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/selectCoachTimePage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("empVO", empVO); // 資料庫取出的VO物件,存入req
			String url = "/back-end/coachtime/coachAddTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllCoachTime.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCoachTime.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 ****************************************/
			Integer timeID = Integer.valueOf(req.getParameter("timeID"));

			/*************************** 2.開始查詢資料 ****************************************/
			CoachTimeService coachSvc = new CoachTimeService();
			CoachTimeVO coachTimeVO = coachSvc.getOneEmp(timeID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("coachTimeVO", coachTimeVO); // 資料庫取出的VO物件,存入req
			String url = "/back-end/coachtime/updateCoachTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listAllCoachTime.jsp
			successView.forward(req, res);
		}

		if ("memberaddcoachorder".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 **********************/
			Integer memid = Integer.valueOf(req.getParameter("memid").trim());
			Integer empid = Integer.valueOf(req.getParameter("empID").trim());
			java.sql.Date coachDate = java.sql.Date.valueOf(req.getParameter("coachDate").trim());
			
			String updatecoachtime = "";

			CoachClassOrderVO coachClassOrderVO = new CoachClassOrderVO();
			CoachTimeVO coachTimeVO = new CoachTimeVO();
			EmpVO empVO = new EmpVO();
			CoachTimeVO coachTimeVO2 = new CoachTimeVO();
			coachClassOrderVO.setMemID(memid);
			coachTimeVO.setEmpID(empid);
			coachTimeVO.setCoachDate(coachDate);
			empVO.setEmpID(empid);
			// Send the use back to the form, if there were errors

			/***************************
			 * 2.�}�l�ק���
			 *****************************************/
			CoachClassOrderService coachSvc = new CoachClassOrderService();
			CoachTimeService coachTimeService = new CoachTimeService();
			coachTimeVO2 = coachTimeService.getCoachTimeByCoachDate(empid, coachDate);
			List<CoachClassOrderVO> list = coachSvc.getAllCoachPeriodByEmpAndClassTime(empid, coachDate);
			
			for (CoachClassOrderVO cco : list) {
				for (int i = 0; i < cco.getCoachclassperiod().length(); i++) {
					if (cco.getCoachclassperiod().charAt(i) == '0') {
						updatecoachtime = coachTimeVO2.getCoachTime().substring(0, i) + '2' + coachTimeVO2.getCoachTime().substring((i + 1));
						coachTimeVO2.setCoachTime(updatecoachtime);
					}
				}
			}
			

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("coachClassOrderVO", coachClassOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				req.setAttribute("coachTimeVO", coachTimeVO);
				req.setAttribute("coachTimeVO2", coachTimeVO2);
				req.setAttribute("empVO", empVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/coachListOne.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}
			/***************************
			 * 3.�ק粒��,�ǳ����(Send the Success view)
			 *************/
			req.setAttribute("coachTimeVO2", coachTimeVO2);
			req.setAttribute("coachClassOrderVO", coachClassOrderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			req.setAttribute("coachTimeVO", coachTimeVO);
			req.setAttribute("empVO", empVO);
			String url = "/front-end/coach/addCoachClassOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自updateCoachTime.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer timeID = Integer.valueOf(req.getParameter("timeID").trim());

			Integer empid = null;
			try {
				empid = Integer.valueOf(req.getParameter("empid").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("員工編號格式錯誤");
				empid = 0;
			}

			String[] coachTime = req.getParameterValues("coachTime");
			String newCoathTime = "";
			if (coachTime == null) {
				errorMsgs.add("請勾選你的時段");
			} else {
				aa: for (int j = 0; j < 24; j++) {
					String ab = String.valueOf(j);

					for (int i = 0; i < coachTime.length; i++) {
						if (coachTime[i].equals(ab)) {
							newCoathTime += "0";
							continue aa;
						}

					}
					if (j == newCoathTime.length()) {
						newCoathTime += "2";
					}
				}
			}

			java.sql.Date coachDate = null;
			try {
				coachDate = java.sql.Date.valueOf(req.getParameter("coachDate").trim());
			} catch (IllegalArgumentException e) {
				coachDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("時間格式錯誤");
			}

			CoachTimeVO coachTimeVO = new CoachTimeVO();
			coachTimeVO.setTimeID(timeID);
			coachTimeVO.setEmpID(empid);
			coachTimeVO.setCoachTime(newCoathTime);
			coachTimeVO.setCoachDate(coachDate);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("coachTimeVO", coachTimeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/updateCoachTime.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CoachTimeService coachSvc = new CoachTimeService();
			List<CoachTimeVO> list = coachSvc.getAllCoachDate(empid);
			try {
				for (CoachTimeVO ctime : list) {
					if (coachDate.equals(ctime.getCoachDate())) {
						System.out.println("相同");
						errorMsgs.add("日期重複了");

						if (!errorMsgs.isEmpty()) {
							req.setAttribute("coachTimeVO", coachTimeVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/coachtime/updateCoachTime.jsp");
							failureView.forward(req, res);
							return;
						}
					}
				}

				coachTimeVO = coachSvc.updateEmp(timeID, empid, newCoathTime, coachDate);

			} catch (NullPointerException e) {
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachTimeVO", coachTimeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/updateCoachTime.jsp");
					failureView.forward(req, res);
					return;
				}
				coachTimeVO = coachSvc.updateEmp(timeID, empid, newCoathTime, coachDate);
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("coachTimeVO", coachTimeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/coachtime/listAllCoachTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllCoachTime.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自coachAddTime.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer empid = null;
			try {
				empid = Integer.valueOf(req.getParameter("empid").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("員工編號格式錯誤");
				empid = 0;
			}

			String[] coachTime = req.getParameterValues("coachTime");
			String newCoathTime = "";
			if (coachTime == null) {
				errorMsgs.add("請勾選你的時段");
			} else {
				aa: for (int j = 0; j < 24; j++) {
					String ab = String.valueOf(j);

					for (int i = 0; i < coachTime.length; i++) {
						if (coachTime[i].equals(ab)) {
							newCoathTime += "0";
							continue aa;
						}

					}
					if (j == newCoathTime.length()) {
						newCoathTime += "2";
					}
				}
			}

			java.sql.Date coachDate = null;
			try {
				coachDate = java.sql.Date.valueOf(req.getParameter("coachDate").trim());
			} catch (IllegalArgumentException e) {
				coachDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("時間格式錯誤");
			}
			CoachTimeVO coachTimeVO = new CoachTimeVO();

			coachTimeVO.setEmpID(empid);
			coachTimeVO.setCoachTime(newCoathTime);
			coachTimeVO.setCoachDate(coachDate);
			// Send the use back to the form, if there were errors

			/*************************** 2.開始修改資料 ***************************************/
			CoachTimeService coachSvc = new CoachTimeService();
			List<CoachTimeVO> list = coachSvc.getAllCoachDate(empid);
//				System.out.println(ctime.getCoachDate());

			try {
				for (CoachTimeVO ctime : list) {
					if (coachDate.equals(ctime.getCoachDate())) {
						errorMsgs.add("日期重複了");
						
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("coachTimeVO", coachTimeVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/coachtime/coachAddTime.jsp");
							failureView.forward(req, res);
							return;
						}
					}
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachTimeVO", coachTimeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/coachAddTime.jsp");
					failureView.forward(req, res);
					return;
				}
				coachTimeVO = coachSvc.addCoach(empid, newCoathTime, coachDate);

			} catch (NullPointerException e) {
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachTimeVO", coachTimeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachtime/coachAddTime.jsp");
					failureView.forward(req, res);
					return;
				}
				coachTimeVO = coachSvc.addCoach(empid, newCoathTime, coachDate);
			}

			/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/coachtime/listAllCoachTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllCoachTime.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自coachAddTime.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer timeID = Integer.valueOf(req.getParameter("timeID"));

			/*************************** 2.開始刪除資料 ***************************************/
			CoachTimeService coachSvc = new CoachTimeService();
			coachSvc.deleteEmp(timeID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/coachtime/listAllCoachTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
