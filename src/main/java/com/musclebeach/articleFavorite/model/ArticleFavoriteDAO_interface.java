package com.musclebeach.articleFavorite.model;

import java.util.List;

public interface ArticleFavoriteDAO_interface {

	public void insert(ArticleFavoriteVO articleFavoriteVO);

	public void delete(Integer artID,Integer memID);

	public List<ArticleFavoriteVO> getAllByMemID(Integer memID);

	public List<ArticleFavoriteVO> getAll();

	public ArticleFavoriteVO findByForeignKey(Integer artID, Integer memID);

}