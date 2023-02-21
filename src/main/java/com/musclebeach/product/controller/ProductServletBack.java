package com.musclebeach.product.controller;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.product.model.entity.Product;
import com.musclebeach.product.model.entity.ProductType;
import com.musclebeach.product.model.service.ProductService;
import com.musclebeach.product.model.service.ProductTypeService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@WebServlet({"/back-end/product/getOneForUpdate", "/back-end/product/delete",
        "/back-end/product/updateProdInput", "/back-end/product/addOneProd",
        "/front-end/product/shopDetail"})
@MultipartConfig
public class ProductServletBack extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final ProductService prodSvc = context.getBean(ProductService.class);
    private final ProductTypeService productTypeServiceBack = context.getBean(ProductTypeService.class);

    public static byte[] getPictureByteArray(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        return buffer;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自shop.jsp的請求
            Integer proID = Integer.valueOf(req.getParameter("proID"));

            Product prodVO = prodSvc.getOneProd(proID);
            req.setAttribute("prodVO", prodVO); // 資料庫取出的prodVO物件,存入req

            String url = "/front-end/product/shopDetail.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 shopDetail.jsp
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addProd.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the req scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String proName = req.getParameter("proName");
            String proNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (proName == null || proName.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!proName.trim().matches(proNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到40之間");
            }
            Integer typeID = Integer.valueOf(req.getParameter("typeID").trim());

            Integer proQty = null;
            try {
                proQty = Integer.valueOf(req.getParameter("proQty"));
            } catch (NumberFormatException e) {
                proQty = 0;
                errorMsgs.add("商品數量請填數字");
            }

            Integer proPrice = null;
            try {
                proPrice = Integer.valueOf(req.getParameter("proPrice"));
            } catch (NumberFormatException e) {
                proPrice = 0;
                errorMsgs.add("價格請填數字");
            }

            String proContent = req.getParameter("proContent").trim();
            if (proContent == null || proContent.trim().length() == 0) {
                errorMsgs.add("商品描述請勿空白");
            }

            Product prodVO = new Product();
            prodVO.setProName(proName);
            prodVO.setTypeID(typeID);
            prodVO.setProQty(proQty);
            prodVO.setProPrice(proPrice);
            prodVO.setProContent(proContent);

            // 接收圖片參數
            Part part = req.getPart("proImg");

            // 將圖片轉為byte陣列
            InputStream in = part.getInputStream();
            byte[] proImg = new byte[in.available()];
            if (proImg.length == 0) {
                in = getServletContext().getResourceAsStream("/back-end/product/images/nopic.png");
                proImg = new byte[in.available()];
            }

            in.read(proImg);
            in.close();

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的prodVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProd.jsp");
                failureView.forward(req, res);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            prodVO = prodSvc.addProdAndFile(proName, typeID, proQty, proPrice, proContent, proImg);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/back-end/product/listAllProd.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProd.jsp
//			successView.forward(req, res);
            //新增商品後會有種類、狀態帶不出值問題，因此改用重導
            String path = req.getContextPath();
            res.sendRedirect(path + "/back-end/product/listAllProd.jsp");
        }

        if ("insert_type".equals(action)) { // 來自addProdType.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the req scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String typeName = req.getParameter("typeName");
            String typeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (typeName == null || typeName.trim().length() == 0) {
                errorMsgs.add("商品類別: 請勿空白");
            } else if (!typeName.trim().matches(typeNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到40之間");
            }

            ProductType prodTypeVO = new ProductType();
            prodTypeVO.setTypeName(typeName);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("prodTypeVO", prodTypeVO); // 含有輸入格式錯誤的prodVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProdType.jsp");
                failureView.forward(req, res);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            prodTypeVO = productTypeServiceBack.addProdType(typeName);
            System.out.println(prodTypeVO);
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/product/listAllProdType.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProdType.jsp
            successView.forward(req, res);
        }


        if ("delete".equals(action)) { // 來自listAllProd.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the req scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer proID = Integer.valueOf(req.getParameter("proID"));

            /*************************** 2.開始刪除資料 ***************************************/
            prodSvc.deleteProd(proID);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/back-end/product/listAllProd.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
            return;
        }


        if ("getOne_For_Update".equals(action)) { // 來自listAllProd.jsp的請求

            Integer proID = Integer.valueOf(req.getParameter("proID"));

            Product prodVO = prodSvc.getOneProd(proID);
            req.setAttribute("prodVO", prodVO);


            String url = "/back-end/product/update_prod_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_prod_input.jsp
            successView.forward(req, res);
            return;
        }
        if ("update".equals(action)) { // 來自update_prod_input.jsp的請求
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the req scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer proID = Integer.valueOf(req.getParameter("proID"));

            String proName = req.getParameter("proName");
            String proNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (proName == null || proName.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!proName.trim().matches(proNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到40之間");
            }

            Integer typeID = null;
            try {
                typeID = Integer.valueOf(req.getParameter("typeID"));
            } catch (NumberFormatException e) {
                typeID = 0;
                errorMsgs.add("商品類別請填數字.");
            }

            Integer proQty = null;
            try {
                proQty = Integer.valueOf(req.getParameter("proQty"));
            } catch (NumberFormatException e) {
                proQty = 0;
                errorMsgs.add("商品數量請填數字");
            }

            Integer proPrice = null;
            try {
                proPrice = Integer.valueOf(req.getParameter("proPrice"));
            } catch (NumberFormatException e) {
                proPrice = 0;
                errorMsgs.add("商品價格請填數字");
            }

            String proContent = req.getParameter("proContent").trim();
            if (proContent == null || proContent.trim().length() == 0) {
                errorMsgs.add("商品描述請勿空白");
            }

            Integer proStatus = null;
            try {
                proStatus = Integer.valueOf(req.getParameter("proStatus").trim());
            } catch (NumberFormatException e) {
                proStatus = 0;
                errorMsgs.add("商品狀態錯誤");
            }

            ProductType productType = new ProductType();
            productType.setTypeID(typeID);

            Product prodVO = new Product();
            prodVO.setProID(proID);
            prodVO.setProName(proName);
            prodVO.setTypeID(typeID);
            prodVO.setProQty(proQty);
            prodVO.setProPrice(proPrice);
            prodVO.setProContent(proContent);
            prodVO.setProStatus(proStatus);

            // 接收圖片參數
            Part part = req.getPart("proImg");
            // 將圖片轉為byte陣列
            InputStream in = part.getInputStream();
            byte[] proImg = new byte[in.available()];
            if (proImg.length == 0) {
                proImg = prodSvc.getOneProdIMG(proID).getProImg();
            }
            in.read(proImg);
            in.close();
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的ProdVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/product/update_prod_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }
            /*************************** 2.開始修改資料 *****************************************/
            prodVO = prodSvc.updateProd(proID, proName, typeID, proQty, proPrice, proContent, proStatus, proImg);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("prodVO", prodVO); // 資料庫update成功後,正確的的prodVO物件,存入req
//			String url = "/back-end/product/listAllProd.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProd.jsp
//			successView.forward(req, res);
            //新增商品後會有種類、狀態帶不出值問題，因此改用重導
            String path = req.getContextPath();
            res.sendRedirect(path + "/back-end/product/listAllProd.jsp");
        }

    }
}
