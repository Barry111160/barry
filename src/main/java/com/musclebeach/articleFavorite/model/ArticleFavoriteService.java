package com.musclebeach.articleFavorite.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleFavoriteService {

	@Resource
	private ArticleFavoriteDAO_interface dao;

	public ArticleFavoriteService() {
		dao = new ArticleFavoriteJDBCDAO();
	}

	public ArticleFavoriteVO addArticleFavorite(Integer artID, Integer memID) {

		ArticleFavoriteVO articleFavoriteVO = new ArticleFavoriteVO();

		articleFavoriteVO.setArtID(artID);
		articleFavoriteVO.setMemID(memID);
	
		dao.insert(articleFavoriteVO);

		return articleFavoriteVO;
	}

	public void deleteArtFavorite(Integer artID, Integer memID) {
		dao.delete(artID, memID);
	}

	public List<ArticleFavoriteVO> getAllByMemID(Integer memID) {
		return dao.getAllByMemID(memID);
	}

	public List<ArticleFavoriteVO> getAll() {
		return dao.getAll();
	}
	
	public ArticleFavoriteVO getOneArticleFavorite(Integer artID, Integer memID) {
		return dao.findByForeignKey(artID,memID);
	}
}
