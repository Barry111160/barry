package com.musclebeach.articleMessage.model;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.mem.model.MemService;
import com.musclebeach.mem.model.MemVO;
import org.springframework.context.ApplicationContext;

import java.sql.Timestamp;

public class ArticleMessageVO implements java.io.Serializable {

    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final MemService memService = ctx.getBean(MemService.class);
    private Integer msgID;
    private Integer memID;
    private Integer artID;
    private String msgContent;
    private Integer msgStatus;
    private Timestamp msgStime;

    public Integer getMsgID() {
        return msgID;
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
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

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Timestamp getMsgStime() {
        return msgStime;
    }

    public void setMsgStime(Timestamp msgStime) {
        this.msgStime = msgStime;
    }
    public MemVO getMemVO(){                //取得會員名稱
        return memService.getOneMem(memID);
    }

}