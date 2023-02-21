package com.musclebeach.articleImg.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleImgService {

	@Resource
	private ArticleImgDAO_interface dao;

	public ArticleImgService() {
		dao = new ArticleImgJDBCDAO();
	}

	public ArticleImgVO addArticleImg(byte[] artImg, Integer artID) {

		ArticleImgVO articleImgVO = new ArticleImgVO();

		articleImgVO.setArtImg(artImg);
		articleImgVO.setArtID(artID);
		dao.insert(articleImgVO);
		return articleImgVO;
	}

	public void addWithArticleImgs(Integer artID, List<byte[]> list) {

		List<ArticleImgVO> articleImgVOList = new ArrayList<>();
		
		for (byte[] artImg : list) {
			ArticleImgVO articleImgVO = new ArticleImgVO();
            articleImgVO.setArtImg(artImg);
            articleImgVO.setArtID(artID);
            articleImgVOList.add(articleImgVO);
        }
		dao.insertImgBatch(articleImgVOList);
		
	}
	
	
	
	public void deleteArticleImg(Integer imgID) {
		dao.delete(imgID);
	}

	public ArticleImgVO updateArticleImg(Integer imgID,byte[] artImg) {

		ArticleImgVO articleImgVO = new ArticleImgVO();
		articleImgVO.setImgID(imgID);
		articleImgVO.setArtImg(artImg);

		return articleImgVO;
	}
	public List<ArticleImgVO> getAllByArtID(Integer artID) {
		return dao.getAllByArtID(artID);
	}

	public List<ArticleImgVO> getAll() {
		return dao.getAll();
	}
	
	public ArticleImgVO getOneArticleImg(Integer imgID) {
		return dao.findByPrimaryKey(imgID);
	}
	
	public ArticleImgVO getOneArticleImgByArtID(Integer artID) {
		return dao.findByArtID(artID);
	}
}
