package com.musclebeach.creditCard.model;

public class CreditCardVO implements java.io.Serializable {
    private Integer ccID;
    private Integer memID;
    private String ccNumber;
    private String ccName;
    private String ccTime;
    private String ccvc;

    public CreditCardVO() {

    }

    public Integer getCcID() {
        return ccID;
    }

    public void setCcID(Integer ccID) {
        this.ccID = ccID;
    }

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getCcTime() {
        return ccTime;
    }

    public void setCcTime(String ccTime) {
        this.ccTime = ccTime;
    }

    public String getCcvc() {
        return ccvc;
    }

    public void setCcvc(String ccvc) {
        this.ccvc = ccvc;
    }

}
