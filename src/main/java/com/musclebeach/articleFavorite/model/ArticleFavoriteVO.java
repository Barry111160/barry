package com.musclebeach.articleFavorite.model;

import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ArticleFavoriteVO implements java.io.Serializable {
	private Integer artID;
	private Integer memID;
	private final ApplicationContext ctx = ApplicationContextUtil.getContext();
	private final com.musclebeach.article.model.ArticleService Asv = ctx.getBean(com.musclebeach.article.model.ArticleService.class);
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
	
    public List<com.musclebeach.article.model.ArticleVO> getArticleVO() {     //取得文章內容

	    return Asv.getAllByArtID(artID);
    }
}
