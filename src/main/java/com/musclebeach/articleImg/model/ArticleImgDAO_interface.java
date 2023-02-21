package com.musclebeach.articleImg.model;

import java.sql.Connection;
import java.util.List;

public interface ArticleImgDAO_interface {

	public void insert(ArticleImgVO articleImgVO);

	public void update(ArticleImgVO articleImgVO);

	public void delete(Integer imgID);

	public List<ArticleImgVO> getAllByArtID(Integer artID);

	public List<ArticleImgVO> getAll();

	public ArticleImgVO findByPrimaryKey(Integer imgID);

	public void insert2(ArticleImgVO articleImgVO, Connection con);

	public ArticleImgVO findByArtID(Integer artID);
	
	public void insertImgBatch(List<ArticleImgVO> articleImgVOList);

}