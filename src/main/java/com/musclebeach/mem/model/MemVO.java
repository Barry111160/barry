package com.musclebeach.mem.model;

import java.sql.Date;

public class MemVO {
    private Integer memID;
    private String memName;
    private String account;
    private String password;
    private String memPhone;
    private Date memBirthday;
    private String memAddress;
    private String memMail;
    private Integer memStatus;
    private Integer memAccess;
    private Date membership;

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemPhone() {
        return memPhone;
    }

    public void setMemPhone(String memPhone) {
        this.memPhone = memPhone;
    }

    public Date getMemBirthday() {
        return memBirthday;
    }

    public void setMemBirthday(Date memBirthday) {
        this.memBirthday = memBirthday;
    }

    public String getMemAddress() {
        return memAddress;
    }

    public void setMemAddress(String memAddress) {
        this.memAddress = memAddress;
    }

    public String getMemMail() {
        return memMail;
    }

    public void setMemMail(String memMail) {
        this.memMail = memMail;
    }

    public Integer getMemStatus() {
        return memStatus;
    }

    public void setMemStatus(Integer memStatus) {
        this.memStatus = memStatus;
    }

    public Integer getMemAccess() {
        return memAccess;
    }

    public void setMemAccess(Integer memAccess) {
        this.memAccess = memAccess;
    }

    public Date getMembership() {
        return membership;
    }

    public void setMembership(Date membership) {
        this.membership = membership;
    }
}
