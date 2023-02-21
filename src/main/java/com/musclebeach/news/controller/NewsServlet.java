package com.musclebeach.news.controller;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.news.model.NewsService;
import com.musclebeach.news.model.NewsVO;
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
import java.util.Set;
@WebServlet("/back-end/news/news.do")
public class NewsServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final NewsService newsSvc = context.getBean(NewsService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("newsID");
            String newsIDReg = "^[0-9]{1,100}$";
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入消息編號");

            } else if (!str.trim().matches(newsIDReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("會員編號: 只能是數字");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            Integer newsID = null;
            try {
                newsID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("消息編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
                /*************************** 2.開始查詢資料 *****************************************/
            }
            NewsVO newsVO = newsSvc.getOneNews(newsID);
            if (newsVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
                /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            }
            req.setAttribute("newsVO", newsVO); // 資料庫取出的newVO物件,存入req
            RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/listOneNews.jsp"); // 成功轉交
            // listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer newsID = Integer.valueOf(req.getParameter("newsID"));

            /*************************** 2.開始查詢資料 ****************************************/
            NewsVO newsVO = newsSvc.getOneNews(newsID);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("newsVO", newsVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/news/update_news_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }
        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            // ==================================有疑慮======================================
            Integer newsID = Integer.valueOf(req.getParameter("news").trim());

            Integer empID = Integer.valueOf(req.getParameter("empID"));
            String empIDReg = "^[(0-9)]$";
            if (empID == null) {
                errorMsgs.add("員工編號: 請勿空白");
            } else if (!empID.toString().matches(empIDReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工編號: 只能輸入數字");
            }

            String newsTitle = req.getParameter("newsTitle").trim();
            if (newsTitle == null || newsTitle.trim().length() == 0) {
                errorMsgs.add("標題請勿空白");
            }

            String newsContent = req.getParameter("newsContent").trim();
            if (newsContent == null || newsContent.trim().length() == 0) {
                errorMsgs.add("消息內容請勿空白");
            }
            java.sql.Timestamp newsTime = java.sql.Timestamp.valueOf(req.getParameter("newsTime"));
            NewsVO newsVO = new NewsVO();
            newsVO.setNewsID(newsID);
            newsVO.setEmpID(empID);
            newsVO.setNewsTitle(newsTitle);
            newsVO.setNewsContent(newsContent);
            newsVO.setNewsTime(newsTime);
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/update_news_input.jsp.jsp");
                failureView.forward(req, res);
                return;
            }
            /*************************** 2.開始修改資料 ***************************************/
            newsVO = newsSvc.updateNews(newsID, empID, newsTitle, newsContent, newsTime);

            /*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("newsVO", newsVO);// 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/news/listOneNews.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllNews.jsp
            successView.forward(req, res);

        }
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            Integer empID = Integer.valueOf(req.getParameter("empID"));
            String enameReg = "^[(0-9)]$";
            if (empID == null || empID.toString().trim().length() == 0) {
                errorMsgs.add("員工編號: 請勿空白");
            } else if (!empID.toString().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工編號: 只能數字");
            }

            String newsTitle = req.getParameter("newsTitle");
            if (newsTitle == null || newsTitle.trim().length() == 0) {
                errorMsgs.add("標題請勿空白");
            }

            String newsContent = req.getParameter("newsContent");
            if (newsContent == null || newsContent.trim().length() == 0) {
                errorMsgs.add("內容請勿空白");
            }

            NewsVO newsVO = new NewsVO();
            newsVO.setEmpID(empID);
            newsVO.setNewsTitle(newsTitle);
            newsVO.setNewsContent(newsContent);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/addNews.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/
            newsVO = newsSvc.addNews(empID, newsTitle, newsContent);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/news/listAllNews.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllNews.jsp
            successView.forward(req, res);
        }
        if ("delete".equals(action)) { // 來自listAllNews.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer newsID = Integer.valueOf(req.getParameter("newsID"));

            /*************************** 2.開始刪除資料 ***************************************/
            newsSvc.deletNews(newsID);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/news/listAllNews.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);

        }
        if ("listNewsByEmpID".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer empID = Integer.valueOf(req.getParameter("empID"));
            String enameReg = "^[(0-9)]$";
            if (empID == null || empID.toString().trim().length() == 0) {
                errorMsgs.add("員工編號: 請勿空白");
            } else if (!empID.toString().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工編號: 只能數字");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 ****************************************/

            Set<NewsVO> set = newsSvc.getNewsByempID(empID);
            if (set.size() == 0) {
                RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/nodata.jsp");
                successView.forward(req, res);
                return;
            }
            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("listNewsByEmpID", set); // 資料庫取出的set物件,存入request
            String url = "/back-end/news/listNewsByEmpID.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("listNewsByNewsTitle".equals(action) || "listNewsByNewsTitleB".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            String newsTitle = req.getParameter("newsTitle");
            if (newsTitle == null || newsTitle.trim().length() == 0) {
                errorMsgs.add("查詢請勿空白");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/xxx");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            /*************************** 2.開始查詢資料 ****************************************/
            Set<NewsVO> set = newsSvc.getNewsByNewsTitle(newsTitle);
            if (set.size() == 0) {
                RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/nodata.jsp");
                successView.forward(req, res);
                return;
            }
            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            if ("listNewsByNewsTitle".equals(action)) {
                req.setAttribute("listNewsByNewsTitle", set); // 資料庫取出的set物件,存入request}
                RequestDispatcher successView = req.getRequestDispatcher("/front-end/news/listNewsByNewsTitle.jsp");
                successView.forward(req, res);
            } else {
                req.setAttribute("listNewsByNewsTitleB", set);
                RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/listNewsByNewsTitle.jsp");
                successView.forward(req, res);
            }
        }
    }
}
