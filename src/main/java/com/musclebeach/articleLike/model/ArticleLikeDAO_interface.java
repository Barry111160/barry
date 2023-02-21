package com.musclebeach.articleLike.model;

import java.util.List;

import com.musclebeach.articleLike.model.ArticleLikeVO;

public interface ArticleLikeDAO_interface {

	public void insert(ArticleLikeVO articleLikeVO);

	public void delete(Integer artID, Integer memID);

	public List<ArticleLikeVO> getAll();

	public List<ArticleLikeVO> getAllByArtID(Integer artID);

	public ArticleLikeVO findByForeignKey(Integer artID, Integer memID);


}