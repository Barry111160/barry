package com.musclebeach.news.model;

import java.util.List;
import java.util.Set;


public interface NewsDAO_interface {
    void insert(NewsVO newsVO);

    void update(NewsVO newsVO);

    void delete(Integer newsID);

    NewsVO findByPrimaryKey(Integer newsID);

    List<NewsVO> getAll();

    Set<NewsVO> getNewsByempID(Integer empID);

    Set<NewsVO> getNewsByNewsTitle(String newsTitle);
}
