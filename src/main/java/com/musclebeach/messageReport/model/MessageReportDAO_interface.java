package com.musclebeach.messageReport.model;

import java.util.List;

public interface MessageReportDAO_interface {

    public void insert(MessageReportVO messageReportVO);

    public void update(MessageReportVO messageReportVO);

    public MessageReportVO findByPrimaryKey(Integer reportID);

    public List<MessageReportVO> getAll();

}