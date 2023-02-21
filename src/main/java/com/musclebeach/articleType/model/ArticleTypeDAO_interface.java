package com.musclebeach.articleType.model;

import java.util.List;

public interface ArticleTypeDAO_interface {

    public void insert(ArticleTypeVO articleTypeVO);

    public ArticleTypeVO findByPrimaryKey(Integer typeID);

    public List<ArticleTypeVO> getAll();

}