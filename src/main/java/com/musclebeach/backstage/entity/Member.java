package com.musclebeach.backstage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Member implements Serializable {
    private Integer memID;
    private String memName;
    private String memAccount;
    private String memPassword;
    private String memPhone;
    private Date memBirthday;
    private String memAddress;
    private String memEmail;
    private Integer memStatus;
    private Timestamp membership;
    private Integer memAccess;
}
