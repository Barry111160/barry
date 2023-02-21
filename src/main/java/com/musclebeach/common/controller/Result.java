package com.musclebeach.common.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result implements Serializable {
    private Integer code;
    private Object data;
    private String msg;
}
