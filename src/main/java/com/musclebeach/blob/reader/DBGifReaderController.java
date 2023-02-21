package com.musclebeach.blob.reader;


import com.musclebeach.articleImg.model.ArticleImgService;
import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/front-end/article/DBGifReader")
public class DBGifReaderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final ArticleImgService articleImgService = ctx.getBean(ArticleImgService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("image/gif");
        ServletOutputStream out = res.getOutputStream();

        try {
            Integer imgID = Integer.valueOf(req.getParameter("imgID"));
            out.write(articleImgService.getOneArticleImg(imgID).getArtImg());
        } catch (Exception e) {
            InputStream in = getServletContext().getResourceAsStream("/front-end/article/resources/NoData/nopic.jpg");
            byte[] buf = new byte[in.available()];
            in.read(buf);
            out.write(buf);
            in.close();

        }
    }

}
