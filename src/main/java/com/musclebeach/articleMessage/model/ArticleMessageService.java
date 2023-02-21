package com.musclebeach.articleMessage.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleMessageService {

    @Resource
    private ArticleMessageDAO_interface dao;

    public ArticleMessageService() {
        dao = new ArticleMessageJDBCDAO();
    }

    public ArticleMessageVO addArticleMessage(Integer artID, Integer memID, String msgContent) {

        ArticleMessageVO articleMessageVO = new ArticleMessageVO();

        articleMessageVO.setArtID(artID);
        articleMessageVO.setMemID(memID);
        articleMessageVO.setMsgContent(msgContent);

        dao.insert(articleMessageVO);

        return articleMessageVO;
    }

    public ArticleMessageVO updateArticleMessage(Integer msgID,String msgContent, Integer msgStatus) {

        ArticleMessageVO articleMessageVO = new ArticleMessageVO();

        articleMessageVO.setMsgID(msgID);
        articleMessageVO.setMsgContent(msgContent);
        articleMessageVO.setMsgStatus(msgStatus);

        dao.update(articleMessageVO);

        return articleMessageVO;
    }

    public ArticleMessageVO getOneArticleMessage(Integer msgID) {
        return dao.findByPrimaryKey(msgID);
    }

    public List<ArticleMessageVO> getAllByArtID(Integer artID) {
        return dao.getAllByArtID(artID);
    }

    public List<ArticleMessageVO> getAll() {
        return dao.getAll();
    }


}
