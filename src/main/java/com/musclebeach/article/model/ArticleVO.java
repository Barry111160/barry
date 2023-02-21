package com.musclebeach.article.model;

import com.musclebeach.articleImg.model.ArticleImgService;
import com.musclebeach.articleLike.model.ArticleLikeService;
import com.musclebeach.articleMessage.model.ArticleMessageService;
import com.musclebeach.articleMessage.model.ArticleMessageVO;
import com.musclebeach.articleType.model.ArticleTypeService;
import com.musclebeach.articleType.model.ArticleTypeVO;
import com.musclebeach.mem.model.MemService;
import com.musclebeach.mem.model.MemVO;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.mem.model.MemVO;
import org.springframework.context.ApplicationContext;

import java.sql.Timestamp;
import java.util.List;

public class ArticleVO implements java.io.Serializable {
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final ArticleMessageService articleMessageService = ctx.getBean(ArticleMessageService.class);
    private final ArticleTypeService articleTypeService = ctx.getBean(ArticleTypeService.class);
    private final ArticleImgService articleImgService = ctx.getBean(ArticleImgService.class);
    private final ArticleLikeService articleLikeService = ctx.getBean(ArticleLikeService.class);
    private final MemService memService = ctx.getBean(MemService.class);


    private Integer artID;
    private Integer memID;
    private Integer typeID;
    private String artTitle;
    private String artContent;
    private Timestamp artStime;
    private Timestamp artLtime;
    private Integer artStatus;

    public Integer getArtID() {
        return artID;
    }

    public void setArtID(Integer artID) {
        this.artID = artID;
    }

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtContent() {
        return artContent;
    }

    public void setArtContent(String artContent) {
        this.artContent = artContent;
    }

    public Timestamp getArtStime() {
        return artStime;
    }

    public void setArtStime(Timestamp artStime) {
        this.artStime = artStime;
    }

    public Timestamp getArtLtime() {
        return artLtime;
    }

    public void setArtLtime(Timestamp artLtime) {
        this.artLtime = artLtime;
    }

    public Integer getArtStatus() {
        return artStatus;
    }

    public void setArtStatus(Integer artStatus) {
        this.artStatus = artStatus;
    }

    public List<ArticleMessageVO> getArticleMessageVO() {  //取得文章留言
        return articleMessageService.getAllByArtID(artID);
    }

    public ArticleTypeVO getArticleTypeVO() {
        return articleTypeService.getOneArticleType(typeID);
    }

    public com.musclebeach.articleImg.model.ArticleImgVO getArticleImgVO() {     //取得單張文章圖片
        return   articleImgService.getOneArticleImgByArtID(artID);}


    public List<com.musclebeach.articleImg.model.ArticleImgVO> getArticleImgListVO() {     //取得多張文章圖片
        return articleImgService.getAllByArtID(artID);
    }
    public List<com.musclebeach.articleLike.model.ArticleLikeVO> getArtLikeVO() {     //取得文章讚數
        return articleLikeService.getAllArtLike(artID);

    }

    public MemVO getMemVO(){                //取得會員名稱
        return memService.getOneMem(memID);
    }
}
