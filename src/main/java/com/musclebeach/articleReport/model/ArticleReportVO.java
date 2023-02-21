package com.musclebeach.articleReport.model;

import java.sql.Timestamp;

public class ArticleReportVO implements java.io.Serializable {
    private Integer reportID;
    private Integer memID;
    private Integer artID;
    private String reportContent;
    private Integer reportStatus;
    private Timestamp reportStime;

    public Integer getReportID() {
        return reportID;
    }

    public void setReportID(Integer reportID) {
        this.reportID = reportID;
    }

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public Integer getArtID() {
        return artID;
    }

    public void setArtID(Integer artID) {
        this.artID = artID;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Timestamp getReportStime() {
        return reportStime;
    }

    public void setReportStime(Timestamp reportStime) {
        this.reportStime = reportStime;
    }
}
