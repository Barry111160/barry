package com.musclebeach.course.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PageBean<T> implements Serializable {
    private Integer totalCount;
    private List<T> list;
}
