package com.musclebeach.course.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ClassSchedule implements Serializable {
    private Integer timeID;
    private TeamClass teamClass;
    private Timestamp startTime;
    private Timestamp endTime;
}
