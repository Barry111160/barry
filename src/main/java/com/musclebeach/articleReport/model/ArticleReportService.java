package com.musclebeach.articleReport.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleReportService {
    @Resource
    private ArticleReportDAO_interface dao;

    public ArticleReportVO addArticleReport(Integer artID, Integer memID, String reportContent) {

        ArticleReportVO articleReportVO = new ArticleReportVO();

        articleReportVO.setArtID(artID);
        articleReportVO.setMemID(memID);
        articleReportVO.setReportContent(reportContent);

        dao.insert(articleReportVO);

        return articleReportVO;
    }

    public ArticleReportVO updateArticleReport(Integer reportID,Integer reportStatus) {

        ArticleReportVO articleReportVO = new ArticleReportVO();

        articleReportVO.setReportID(reportID);

        articleReportVO.setReportStatus(reportStatus);

        dao.update(articleReportVO);

        return articleReportVO;
    }

    public void deleteArticleReport(Integer report_id) {
        dao.delete(report_id);
    }

    public ArticleReportVO getOneArticleReport(Integer reportID) {
        return dao.findByPrimaryKey(reportID);
    }

    public List<ArticleReportVO> getAll() {
        return dao.getAll();
    }
}
