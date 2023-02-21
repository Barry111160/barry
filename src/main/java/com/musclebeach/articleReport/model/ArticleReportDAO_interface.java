package com.musclebeach.articleReport.model;

import java.util.List;

public interface ArticleReportDAO_interface {

    void insert(ArticleReportVO articleReportVO);

    void update(ArticleReportVO articleReportVO);

    void delete(Integer reportID);

    ArticleReportVO findByPrimaryKey(Integer reportID);

    List<ArticleReportVO> getAll();

}