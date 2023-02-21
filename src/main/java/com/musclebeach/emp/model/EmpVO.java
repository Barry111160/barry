package com.musclebeach.emp.model;

import java.sql.Date;

public class EmpVO implements java.io.Serializable {
    private Integer empID;
    private String password;
    private String empName;
    private Date hiredate;
    private Date empBirthday;
    private String empPhone;
    private String empMail;
    private Integer empStatus;
    private Integer coachPrice;
    private byte[] empImg;
    private String coachContent;

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getEmpBirthday() {
        return empBirthday;
    }

    public void setEmpBirthday(Date empBirthday) {
        this.empBirthday = empBirthday;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public Integer getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Integer empStatus) {
        this.empStatus = empStatus;
    }

    public Integer getCoachPrice() {
        return coachPrice;
    }

    public void setCoachPrice(Integer coachPrice) {
        this.coachPrice = coachPrice;
    }

    public byte[] getEmpImg() {
        return empImg;
    }

    public void setEmpImg(byte[] empImg) {
        this.empImg = empImg;
    }

    public String getCoachContent() {
        return coachContent;
    }

    public void setCoachContent(String coachContent) {
        this.coachContent = coachContent;
    }


}
