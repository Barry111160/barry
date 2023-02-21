package com.musclebeach.classOrder.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ClassOrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderID;
    private Integer empID;
    private Integer memID;
    private Integer classID;
    private Integer orderStatus;
    private Timestamp createTime;
    private Timestamp updateTime;

    public ClassOrderVO() {
    }

    public ClassOrderVO(Integer orderID, Integer empID, Integer memID, Integer classID, Integer orderStatus,
                        Timestamp createTime, Timestamp updateTime) {
        this.orderID = orderID;
        this.empID = empID;
        this.memID = memID;
        this.classID = classID;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
