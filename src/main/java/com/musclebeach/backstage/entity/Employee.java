package com.musclebeach.backstage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Employee implements Serializable {
    private Integer empID;
    private String empPassword;
    private String empName;
    private Date empHiredate;
    private Date empBirthday;
    private String empPhone;
    private String empEmail;
    private Boolean empStatus;
    private Integer empCoachPrice;
    private FileInputStream empImg;
}
