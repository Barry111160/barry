package com.musclebeach.course.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musclebeach.backstage.entity.Employee;
import com.musclebeach.backstage.entity.Member;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ClassOrder implements Serializable {
    private Integer orderID;
    private Employee employee;
    private Member member;
    private TeamClass teamClass;
    private Integer orderStatus;
    private Timestamp createTime;
    private Timestamp updateTime;
}
