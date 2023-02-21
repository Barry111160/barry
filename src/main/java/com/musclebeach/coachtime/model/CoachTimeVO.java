package com.musclebeach.coachtime.model;

import java.sql.Date;

public class CoachTimeVO implements java.io.Serializable {
    private Integer timeID;
    private Integer empID;
    private String coachTime;
    private Date coachDate;

    public Integer getTimeID() {
        return timeID;
    }

    public void setTimeID(Integer timeID) {
        this.timeID = timeID;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getCoachTime() {
        return coachTime;
    }

    public void setCoachTime(String coachTime) {
        this.coachTime = coachTime;
    }

    public Date getCoachDate() {
        return coachDate;
    }

    public void setCoachDate(Date coachDate) {
        this.coachDate = coachDate;
    }

}