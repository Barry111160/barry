package com.musclebeach.article.model;

import com.musclebeach.articleImg.model.ArticleImgVO;

import java.util.List;
import java.util.Map;

public interface ArticleDAO_interface {

    public void insert(ArticleVO articleVO);

    public void update(ArticleVO articleVO);

    public ArticleVO findByPrimaryKey(Integer artID);

    public List<ArticleVO> getAll();

    public List<ArticleVO> getAll(Map<String, String[]> map);

    public void insertWithArticleImgs(ArticleVO articleVO, List<ArticleImgVO> list);

    public List<ArticleVO> getAllByTypeID(Integer typeID);

    public List<ArticleVO> getAllByArticleTitleOrArticleContent(String artTitle,String artContent);

    public List<ArticleVO> getAllByArtID(Integer artID);

    public List<ArticleVO> getAllByMemID(Integer memID);

}