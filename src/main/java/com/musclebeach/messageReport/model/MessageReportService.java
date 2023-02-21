package com.musclebeach.messageReport.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageReportService {
    @Resource
    private MessageReportDAO_interface dao;

    public MessageReportVO addMessageReport(Integer memID, Integer msgID, String reportContent) {

        MessageReportVO messageReportVO = new MessageReportVO();

        messageReportVO.setMemID(memID);
        messageReportVO.setMsgID(msgID);
        messageReportVO.setReportContent(reportContent);

        dao.insert(messageReportVO);

        return messageReportVO;
    }

    public MessageReportVO updateMessageReport(Integer reportStatus, Integer reportID) {

        MessageReportVO messageReportVO = new MessageReportVO();

        messageReportVO.setReportStatus(reportStatus);
        messageReportVO.setReportID(reportID);

        dao.update(messageReportVO);

        return messageReportVO;
    }

    public MessageReportVO getOneMessageReport(Integer reportID) {
        return dao.findByPrimaryKey(reportID);
    }

    public List<MessageReportVO> getAll() {
        return dao.getAll();
    }
}
