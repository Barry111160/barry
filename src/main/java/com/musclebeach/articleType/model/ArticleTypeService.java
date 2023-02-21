package com.musclebeach.articleType.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleTypeService {
    @Resource
    private ArticleTypeDAO_interface dao;

    public ArticleTypeVO addArticleType(String typeName) {

        ArticleTypeVO articleTypeVO = new ArticleTypeVO();

        articleTypeVO.setTypeName(typeName);

        dao.insert(articleTypeVO);

        return articleTypeVO;
    }

    public ArticleTypeVO getOneArticleType(Integer typeID) {
        return dao.findByPrimaryKey(typeID);
    }

    public List<ArticleTypeVO> getAll() {
        return dao.getAll();
    }
}
