package com.musclebeach.common.controller;

public interface Code {
    Integer CREATE_OK = 20011;
    Integer READ_OK = 20021;
    Integer UPDATE_OK = 20031;
    Integer DELETE_OK = 20041;
    Integer CREATE_ERR = 20010;
    Integer READ_ERR = 20020;
    Integer UPDATE_ERR = 20030;
    Integer DELETE_ERR = 20040;

    Integer SYSTEM_ERR = 50010;
    Integer BUSINESS_ERR = 50020;
    Integer OTHER_ERR = 50030;
}
