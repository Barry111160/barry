package com.musclebeach.article.controller;

import com.musclebeach.article.model.ArticleService;
import com.musclebeach.article.model.ArticleVO;
import com.musclebeach.articleFavorite.model.ArticleFavoriteService;
import com.musclebeach.articleFavorite.model.ArticleFavoriteVO;
import com.musclebeach.articleLike.model.ArticleLikeService;
import com.musclebeach.articleLike.model.ArticleLikeVO;
import com.musclebeach.articleMessage.model.ArticleMessageService;
import com.musclebeach.articleMessage.model.ArticleMessageVO;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.mem.model.MemService;
import com.musclebeach.mem.model.MemVO;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@WebServlet({"/back-end/article/article.do", "/front-end/article/article.do"})
@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class ArticleServlet extends HttpServlet {

    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final ArticleService articleService = ctx.getBean(ArticleService.class);

    private final ArticleFavoriteService articleFavoriteService = ctx.getBean(ArticleFavoriteService.class);
    private final ArticleLikeService artLikeService = ctx.getBean(ArticleLikeService.class);
    private final MemService memService = ctx.getBean(MemService.class);

    private final ArticleMessageService articleMessageSvc = ctx.getBean(ArticleMessageService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("login".equals(action)) { // from login.jsp
            List<String> errorAccount = new LinkedList<String>();
            req.setAttribute("errorAccount", errorAccount);

            /*********************** 1.?????????????????? - ??????????????????????????? *************************/
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            if (account == null || account.trim().length() == 0) {
                errorAccount.add("???????????????????????????");
            } else if (password == null || password.trim().length() == 0) {
                errorAccount.add("???????????????????????????");
            }
            if (!errorAccount.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.?????????????????? ***************************************/

            MemVO memVO = memService.getAccount(account);
            if (memVO == null) {
                errorAccount.add("???????????????");
            } else if (!password.equals(memVO.getPassword())) {
                errorAccount.add("??????????????????");
            } else if (memVO.getMemStatus() == 0) {
                errorAccount.add("?????????????????????????????????????????????????????????");
            } else if (memVO.getMemStatus() == 2) {
                errorAccount.add("????????????????????????????????????");
            }
            if (!errorAccount.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
                failureView.forward(req, res);
                return;
            }

            /********** ?????????????????? **********/
            if (memVO.getMemAccess() == 1) {
                // ????????????
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date date = calendar.getTime();
                // ????????????
                java.sql.Date sqlDate = memVO.getMembership();
                Date utilDate = new Date(sqlDate.getTime());
//				System.out.println(date);
//				System.out.println(sqlDate);
                if (date.compareTo(utilDate) > 0) {
                    memService.updateMembership(memVO.getMemID());
                }
            }

            /*************************** 3.????????????,????????????(Send the Success view) ***********/
            HttpSession session = req.getSession(false);
            MemVO memVO2 = memService.getAccount(account);
            session.setAttribute("memVO", memVO2);

            String url = "/front-end/article/listAllArticle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("getOne_For_Display".equals(action)) { // ?????????????????? list.jsp?????????


            /*************************** 1.?????????????????? - ??????????????????????????? **********************/
            Integer artID = Integer.valueOf(req.getParameter("artID").trim());
            Integer memID = Integer.valueOf(req.getParameter("memID").trim());

            /*************************** 2.?????????????????? *****************************************/

            ArticleVO articleVO = articleService.getOneArticleVO(artID);


            List<ArticleMessageVO> articleMessageVO = articleMessageSvc.getAllByArtID(artID);

            /*************************** 3.????????????,????????????(Send the Success view) *************/
            req.setAttribute("articleMessageVO", articleMessageVO);
            req.setAttribute("articleVO", articleVO);
            String url = "/front-end/article/listOneArticle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????? listOneArticle.jsp
            successView.forward(req, res);
        }


        if ("getOne_For_Display_For_Alter_Article".equals(action)) { // ?????????????????? list.jsp?????????

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.?????????????????? - ??????????????????????????? **********************/
            Integer artID = Integer.valueOf(req.getParameter("artID").trim());
            Integer memID = Integer.valueOf(req.getParameter("memID").trim());

            /*************************** 2.?????????????????? *****************************************/

            ArticleVO articleVO = articleService.getOneArticleVO(artID);

            /*************************** 3.????????????,????????????(Send the Success view) *************/
            req.setAttribute("articleVO", articleVO); // ??????????????????empVO??????,??????req
            String url = "/front-end/article/alterArticle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????? listOneArticle.jsp
            successView.forward(req, res);
        }




        if ("getOne_For_Display_BY_TypeID".equals(action)) { // ?????????????????? list.jsp????????? ??????????????????

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.?????????????????? - ??????????????????????????? **********************/
            Integer typeID = Integer.valueOf(req.getParameter("typeID").trim());

            /*************************** 2.?????????????????? *****************************************/

            List<ArticleVO> listByTypeID = articleService.getAllByTypeID(typeID);

            /*************************** 3.????????????,????????????(Send the Success view) *************/
            req.setAttribute("listByTypeID", listByTypeID); // ??????????????????empVO??????,??????req
            String url = "/front-end/article/listAllByArticleType.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????? listOneArticle.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Display_BY_Article_Title_OR_Article_cintent".equals(action)) { // ?????????????????? list.jsp????????? ??????????????????

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.?????????????????? - ??????????????????????????? **********************/
            String artTitle = req.getParameter("artTitle").trim();
            String artContent = artTitle;
            /*************************** 2.?????????????????? *****************************************/

            List<ArticleVO> listBySearch = articleService.getAllByArticleTitleOrArticleContent(artTitle,artContent);

            /*************************** 3.????????????,????????????(Send the Success view) *************/
            req.setAttribute("listBySearch", listBySearch); // ??????????????????empVO??????,??????req
            String url = "/front-end/article/listAllArticleBySearch.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????? listOneArticle.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // ??????alterArticle.jsp?????????

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.?????????????????? ****************************************/

            Integer	artID = Integer.valueOf(req.getParameter("artID").trim());

            Integer memID = null;
            try {
                memID = Integer.valueOf(req.getParameter("memID").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("????????????????????????.");
            }

            Integer typeID = null;
            try {
                typeID = Integer.valueOf(req.getParameter("typeID").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("???????????????");
            }

            String artTitle = req.getParameter("artTitle");
            String artTitleReg = "^.{1,40}$";
            if (artTitle == null || artTitle.trim().length() == 0) {
                errorMsgs.add("????????????: ????????????");
            } else if (!artTitle.trim().matches(artTitleReg)) { // ??????????????????(???)?????????(regular-expression)
                errorMsgs.add("???????????????1???40??????");
            }

            String artContent = req.getParameter("artContent");
            String artContentReg = "^.{1,2000}$";
            if (artContent == null || artContent.trim().length() == 0) {
                errorMsgs.add("????????????: ????????????");
            } else if (!artContent.trim().matches(artContentReg)) { // ??????????????????(???)?????????(regular-expression)
                errorMsgs.add("???????????????1???2000??????");
            }

            ArticleVO articleVO = new ArticleVO();
            articleVO.setArtID(artID);
            articleVO.setMemID(memID);
            articleVO.setTypeID(typeID);
            articleVO.setArtTitle(artTitle);
            articleVO.setArtContent(artContent);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("articleVO", articleVO); // ???????????????????????????articleVO??????,?????????req
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/alterArticle.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }
            /*************************** 2.?????????????????? ****************************************/

            articleVO = articleService.updateArticle(artID, memID, typeID, artTitle, artContent, 1);

            /*************************** 3.????????????,????????????(Send the Success view) ************/
            req.setAttribute("articleVO", articleVO); // ??????????????????empVO??????,??????req
            String url = "/front-end/article/listOneArticle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????? update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("updateStatus".equals(action)) { // ??????update_emp_input.jsp?????????

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.?????????????????? - ??????????????????????????? **********************/
            Integer artID = Integer.valueOf(req.getParameter("artID").trim());
            Integer memID = Integer.valueOf(req.getParameter("memID").trim());
            Integer typeID = Integer.valueOf(req.getParameter("typeID").trim());
            String artTitle = req.getParameter("artTitle").trim();
            String artContent = req.getParameter("artContent").trim();
            Integer artStatus = Integer.valueOf(req.getParameter("artStatus").trim());

            java.sql.Timestamp artStime = null;
            try {
                artStime = java.sql.Timestamp.valueOf(req.getParameter("artStime").trim());
            } catch (IllegalArgumentException e) {
                artStime = new java.sql.Timestamp(System.currentTimeMillis());
                errorMsgs.add("???????????????!");
            }
            java.sql.Timestamp artLtime = null;
            try {
                artLtime = java.sql.Timestamp.valueOf(req.getParameter("artLtime").trim());
            } catch (IllegalArgumentException e) {
                artLtime = new java.sql.Timestamp(System.currentTimeMillis());
                errorMsgs.add("???????????????!");
            }

            ArticleVO articleVO = new ArticleVO();
            articleVO.setArtID(artID);
            articleVO.setMemID(memID);
            articleVO.setTypeID(typeID);
            articleVO.setArtTitle(artTitle);
            articleVO.setArtContent(artContent);
            articleVO.setArtStatus(artStatus);
            articleVO.setArtStime(artStime);
            articleVO.setArtLtime(artLtime);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("articleVO", articleVO); // ???????????????????????????empVO??????,?????????req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/update_article_input.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }

            /*************************** 2.?????????????????? *****************************************/

            articleVO = articleService.updateArticle(artID, memID, typeID, artTitle, artContent, artStatus);
            articleVO = articleService.getOneArticleVO(artID);

            /*************************** 3.????????????,????????????(Send the Success view) *************/
            req.setAttribute("articleVO", articleVO); // ?????????update?????????,????????????empVO??????,??????req
            String url = "/back-end/article/listOneArticle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????????,??????listOneArticle.jsp
            successView.forward(req, res);
        }

        if ("insertwithImg".equals(action)) { // ??????addArticle.jsp????????? ?????????????????????

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

//				/***********************1.?????????????????? - ???????????????????????????*************************/

            Integer memID = null;
            try {
                memID = Integer.valueOf(req.getParameter("memID").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("????????????????????????.");
            }

            Integer typeID = null;
            try {
                typeID = Integer.valueOf(req.getParameter("typeID").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("???????????????");
            }

            String artTitle = req.getParameter("artTitle");
            String artTitleReg = "^.{1,40}$";
            if (artTitle == null || artTitle.trim().length() == 0) {
                errorMsgs.add("????????????: ????????????");
            } else if (!artTitle.trim().matches(artTitleReg)) { // ??????????????????(???)?????????(regular-expression)
                errorMsgs.add("???????????????1???40??????");
            }

            String artContent = req.getParameter("artContent");
            String artContentReg = "^.{1,2000}$";
            if (artContent == null || artContent.trim().length() == 0) {
                errorMsgs.add("????????????: ????????????");
            } else if (!artContent.trim().matches(artContentReg)) { // ??????????????????(???)?????????(regular-expression)
                errorMsgs.add("???????????????1???2000??????");
            }

            ArticleVO articleVO = new ArticleVO();
            articleVO.setMemID(memID);
            articleVO.setTypeID(typeID);
            articleVO.setArtTitle(artTitle);
            articleVO.setArtContent(artContent);

            // ??????
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
//				if (artImgs.isEmpty()) {
//					errorMsgs.add("????????????: ???????????????");
//				}

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("articleVO", articleVO); // ???????????????????????????empVO??????,?????????req
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }

            /*************************** 2.?????????????????? ***************************************/

            articleVO = articleService.addWithArticleImgs(memID, typeID, artTitle, artContent, artImgs);

            /*************************** 3.????????????,????????????(Send the Success view) ***********/

            String url = "/front-end/article/listAllArticle.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????????,??????listOneArticle.jsp
            successView.forward(req, res);

        }

        if ("listArticles_ByCompositeQuery".equals(action)) { // ??????select_page.jsp?????????????????????
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.?????????????????????Map **********************************/
            // ??????Map<String,String[]> getParameterMap()?????????
            // ??????:an immutable java.util.Map
            // Map<String, String[]> map = req.getParameterMap();
            HttpSession session = req.getSession();
            Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

            // ????????? if ????????????????????????????????????
            if (req.getParameter("whichPage") == null) {
                Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
                session.setAttribute("map", map1);
                map = map1;
            }

            /*************************** 2.?????????????????? ***************************************/

            List<ArticleVO> list = articleService.getAll(map);

            /*************************** 3.????????????,????????????(Send the Success view) ************/
            req.setAttribute("listArticles_ByCompositeQuery", list); // ??????????????????list??????,??????request
            RequestDispatcher successView = req
                    .getRequestDispatcher("/front-end/article/listArticles_ByCompositeQuery.jsp"); // ????????????listEmps_ByCompositeQuery.jsp
            successView.forward(req, res);
        }
    }
}
