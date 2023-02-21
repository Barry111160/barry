package com.musclebeach.articleLike.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.musclebeach.articleLike.model.*;
import java.util.List;
@Service
public class ArticleLikeService {

	@Resource
	private ArticleLikeDAO_interface dao;

	public ArticleLikeService() {
		dao = new ArticleLikeJDBCDAO();
	}

	public ArticleLikeVO addArtLike(Integer artID, Integer memID) {

		ArticleLikeVO artLikeVO = new ArticleLikeVO();

		artLikeVO.setArtID(artID);
		artLikeVO.setMemID(memID);
		dao.insert(artLikeVO);

		return artLikeVO;
	}

	public void deleteArtLike(Integer artID, Integer memID) {
		dao.delete(artID, memID);
	}

	public List<ArticleLikeVO> getAllArtLike(Integer artID) {
		return dao.getAllByArtID(artID);
	}

	public List<ArticleLikeVO> getAll() {
		return dao.getAll();
	}
	
	public ArticleLikeVO getOneArticleLike(Integer artID, Integer memID) {
		return dao.findByForeignKey(artID,memID);
	}
}