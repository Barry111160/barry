package com.musclebeach.articleMessage.model;

import java.util.List;

public interface ArticleMessageDAO_interface {

    public void insert(ArticleMessageVO articleMessageVO);

    public void update(ArticleMessageVO articleMessageVO);

    public ArticleMessageVO findByPrimaryKey(Integer msgID);

    public List<ArticleMessageVO> getAll();

    public List<ArticleMessageVO> getAllByArtID(Integer artID);

}