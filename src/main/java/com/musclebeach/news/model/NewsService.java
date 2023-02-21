package com.musclebeach.news.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
public class NewsService {
    @Resource
    private NewsDAO_interface dao;

    public NewsVO addNews(Integer empID, String newsTitle, String newsContent) {

        NewsVO newsVO = new NewsVO();

        newsVO.setEmpID(empID);
        newsVO.setNewsTitle(newsTitle);
        newsVO.setNewsContent(newsContent);
        dao.insert(newsVO);

        return newsVO;
    }

    public NewsVO updateNews(Integer newsID, Integer empID, String newsTitle, String newsContent, Timestamp newsTime) {
        NewsVO newsVO = new NewsVO();

        newsVO.setNewsID(newsID);
        newsVO.setEmpID(empID);
        newsVO.setNewsTitle(newsTitle);
        newsVO.setNewsContent(newsContent);
        newsVO.setNewsTime(newsTime);
        dao.update(newsVO);

        return newsVO;
    }

    public void deletNews(Integer newsID) {
        dao.delete(newsID);
    }

    public NewsVO getOneNews(Integer newsID) {
        return dao.findByPrimaryKey(newsID);

    }

    public Set<NewsVO> getNewsByempID(Integer empID) {
        return dao.getNewsByempID(empID);

    }

    public Set<NewsVO> getNewsByNewsTitle(String newsTitle) {
        return dao.getNewsByNewsTitle(newsTitle);

    }

    public List<NewsVO> getAll() {
        return dao.getAll();
    }
}
