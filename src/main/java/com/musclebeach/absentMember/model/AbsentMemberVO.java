package com.musclebeach.absentMember.model;

import com.musclebeach.classSchedule.model.ClassScheduleService;
import com.musclebeach.common.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;

public class AbsentMemberVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ApplicationContext ctx = ApplicationContextUtil.getContext();
    private final ClassScheduleService cssv = ctx.getBean(ClassScheduleService.class);
    private Integer timeID;
    private Integer memID;
    private Integer classID;
    private Integer count;

    public AbsentMemberVO() {
    }

    public AbsentMemberVO(Integer timeID, Integer memID) {
        this.timeID = timeID;
        this.memID = memID;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTimeID() {
        return timeID;
    }

    public void setTimeID(Integer timeID) {
        this.timeID = timeID;
    }

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public com.musclebeach.classSchedule.model.ClassScheduleVO getCScheduleVO() {
        return cssv.getOne(timeID);
    }


}
