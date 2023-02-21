package com.musclebeach.question.controller;


import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.question.model.QuestionService;
import com.musclebeach.question.model.QuestionVO;
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
@WebServlet("/back-end/question/question.do")
public class QuestionServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final QuestionService questionSvc = context.getBean(QuestionService.class);
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
            String str = req.getParameter("questionID");
            String questionIDReg = "^[0-9]{1,100}$";
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入消息編號");

            } else if (!str.trim().matches(questionIDReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("會員編號: 只能是數字");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/question/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            Integer questionID = null;
            try {
                questionID = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("消息編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/question/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
                /*************************** 2.開始查詢資料 *****************************************/
            }
            QuestionVO questionVO = questionSvc.getOneQuestion(questionID);
            if (questionVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/question/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
                /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            }
            req.setAttribute("questionVO", questionVO); // 資料庫取出的newVO物件,存入req
            RequestDispatcher successView = req.getRequestDispatcher("/back-end/question/listOneQuestion.jsp"); // 成功轉交
            // listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer questionID = Integer.valueOf(req.getParameter("questionID"));

            /*************************** 2.開始查詢資料 ****************************************/
            QuestionVO questionVO = questionSvc.getOneQuestion(questionID);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("questionVO", questionVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/question/update_question_input.jsp";
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
            Integer questionID = Integer.valueOf(req.getParameter("question").trim());
            String questionTitle = req.getParameter("questionTitle").trim();
            if (questionTitle == null || questionTitle.trim().length() == 0) {
                errorMsgs.add("標題請勿空白");
            }

            String questionContent = req.getParameter("questionContent").trim();
            if (questionContent == null || questionContent.trim().length() == 0) {
                errorMsgs.add("消息內容請勿空白");
            }
            QuestionVO questionVO = new QuestionVO();
            questionVO.setQuestionTitle(questionTitle);
            questionVO.setQuestionContent(questionContent);
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("questionVO", questionVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/question/update_question_input.jsp");
                failureView.forward(req, res);
                return;
            }
            /*************************** 2.開始修改資料 ***************************************/
            questionVO = questionSvc.updateQuestion(questionID, questionTitle, questionContent);

            /*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("questionVO", questionVO);// 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/question/listOneQuestion.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllQuestion.jsp
            successView.forward(req, res);

        }
        if ("insert".equals(action)) { // 來自addquestion.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

            String questionTitle = req.getParameter("questionTitle");
            if (questionTitle == null || questionTitle.trim().length() == 0) {
                errorMsgs.add("標題請勿空白");
            }

            String questionContent = req.getParameter("questionContent");
            if (questionContent == null || questionContent.trim().length() == 0) {
                errorMsgs.add("內容請勿空白");
            }

            QuestionVO questionVO = new QuestionVO();
            questionVO.setQuestionTitle(questionTitle);
            questionVO.setQuestionContent(questionContent);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("questionVO", questionVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/question/addQuestion.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始新增資料 ***************************************/
            questionVO = questionSvc.addQuestion(questionTitle, questionContent);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/question/listAllQuestion.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllQuestion.jsp
            successView.forward(req, res);
        }
        if ("delete".equals(action)) { // 來自listAllQuestion.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer questionID = Integer.valueOf(req.getParameter("questionID"));

            /*************************** 2.開始刪除資料 ***************************************/
            questionSvc.deletQuestion(questionID);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/question/listAllQuestion.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);

        }
        if ("listQuestionByQuestionContent".equals(action) || "listQuestionByQuestionContentB".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            String QuestionContent = req.getParameter("questionContent");

            if (QuestionContent == null || QuestionContent.trim().length() == 0) {
                errorMsgs.add("查詢內容請勿空白");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/question/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 ****************************************/

            Set<QuestionVO> set = questionSvc.getQuestionByQuestionContent(QuestionContent);
            if (set.size() == 0) {
                if ("listQuestionByQuestionContentB".equals(action)) {
                    RequestDispatcher successView = req.getRequestDispatcher("/back-end/question/nodata.jsp");
                    successView.forward(req, res);
                    return;
                } else {
                    RequestDispatcher successView = req.getRequestDispatcher("/front-end/question/nodata.jsp");
                    successView.forward(req, res);
                    return;

                }
            }
            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            if ("listQuestionByQuestionContentB".equals(action)) {
                req.setAttribute("listQuestionByQuestionContentB", set); // 資料庫取出的set物件,存入request
                RequestDispatcher successView = req
                        .getRequestDispatcher("/back-end/question/listQuestionByQuestionContent.jsp");
                successView.forward(req, res);
            } else {
                req.setAttribute("listQuestionByQuestionContent", set); // 資料庫取出的set物件,存入request
                RequestDispatcher successView = req
                        .getRequestDispatcher("/front-end/question/listQuestionByQuestionContent.jsp");
                successView.forward(req, res);

            }

        }
        if ("listQuestionByQuestionTitle".equals(action) || "listQuestionByQuestionTitleB".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            String questionTitle = req.getParameter("questionTitle");
            if (questionTitle == null || questionTitle.trim().length() == 0) {
                errorMsgs.add("查詢請勿空白");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/question/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            /*************************** 2.開始查詢資料 ****************************************/
            Set<QuestionVO> set = questionSvc.getQuestionByQuestionTitle(questionTitle);
            if (set.size() == 0) {
                RequestDispatcher successView = req.getRequestDispatcher("/back-end/question/nodata.jsp");
                successView.forward(req, res);
                return;
            }
            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            if ("listQuestionByQuestionTitle".equals(action)) {
                req.setAttribute("listQuestionByQuestionTitle", set); // 資料庫取出的set物件,存入request}
                RequestDispatcher successView = req
                        .getRequestDispatcher("/fornt-end/question/listQuestionByQuestionTitle.jsp");
                successView.forward(req, res);
            } else {
                req.setAttribute("listQuestionByQuestionTitleB", set);
                RequestDispatcher successView = req
                        .getRequestDispatcher("/back-end/question/listQuestionByQuestionTitle.jsp");
                successView.forward(req, res);
            }
        }
    }
}
