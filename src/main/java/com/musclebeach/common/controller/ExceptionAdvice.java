package com.musclebeach.common.controller;

import com.musclebeach.common.exception.BusinessException;
import com.musclebeach.common.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e) {
        // 紀錄日誌
        // 發送消息給維運
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e) {
        return new Result(e.getCode(), null, e.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public Result doException(Exception e) {
//        // 紀錄日誌
//        // 發送消息給維運
//        return new Result(Code.OTHER_ERR, null, "系統繁忙，請稍後再試!");
//    }
}
