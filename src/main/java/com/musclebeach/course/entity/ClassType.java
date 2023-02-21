package com.musclebeach.course.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ClassType {
    private Integer typeID;
    private String typeName;
}
