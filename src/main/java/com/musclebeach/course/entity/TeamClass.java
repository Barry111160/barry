package com.musclebeach.course.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musclebeach.backstage.entity.Employee;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TeamClass implements Serializable {
    private Integer classID;
    private Employee employee;
    private ClassType classType;
    private String className;
    private Integer peopleMax;
    private String classContent;
    private Boolean classStatus;
}
