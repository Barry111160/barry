package com.musclebeach.classSchedule.model;

import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.sql.Timestamp;

public class ClassScheduleVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final com.musclebeach.classOrder.model.ClassOrderService cosv = ctx.getBean(com.musclebeach.classOrder.model.ClassOrderService.class);
    private final com.musclebeach.teamClass.model.TeamClassService tcsv = ctx.getBean(com.musclebeach.teamClass.model.TeamClassService.class);

    private Integer timeID;
    private Integer classID;
    private Timestamp startTime;
    private Timestamp endTime;

    private Integer memID;
    private String className;
    private Integer classCount;


    public ClassScheduleVO() {

    }

    public ClassScheduleVO(Integer timeID, Integer classID, Timestamp startTime, Timestamp endTime) {
        this.timeID = timeID;
        this.classID = classID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public Integer getTimeID() {
        return timeID;
    }

    public void setTimeID(Integer timeID) {
        this.timeID = timeID;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    //for join memID from classID
    public com.musclebeach.classOrder.model.ClassOrderVO getClassOrderVO() {
        return cosv.getOne(classID);
    }

    //for join teamName from classID
    public com.musclebeach.teamClass.model.TeamClassVO getTeamClassVO() {
        return tcsv.getOne(classID);
    }
}
