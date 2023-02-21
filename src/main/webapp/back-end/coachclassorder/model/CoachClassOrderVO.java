package com.coachclassorder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CoachClassOrderVO implements java.io.Serializable {
	private Integer orderID;
	private Integer empID;
	private Integer memID;
	private Timestamp createTime;
	private Integer orderstatus;
	private Date classTime;
	private Timestamp updateTime;
	private String coachclassperiod;
	public Integer getOrderID() {
		return orderID;
	}
	public String getCoachclassperiod() {
		return coachclassperiod;
	}
	public void setCoachclassperiod(String coachclassperiod) {
		this.coachclassperiod = coachclassperiod;
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
	public Timestamp getCreateTime() {
		return  createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}
	public Date getClassTime() {
		return classTime;
	}
	public void setClassTime(Date classTime) {
		this.classTime = classTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	

}
