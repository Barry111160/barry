package com.musclebeach.course.controller;

import com.musclebeach.common.controller.Code;
import com.musclebeach.common.controller.Result;
import com.musclebeach.course.entity.ClassType;
import com.musclebeach.course.service.ClassTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/classTypes")
public class ClassTypeController {
    @Resource
    private ClassTypeService service;

    @GetMapping
    public Result selectAll() {
        List<ClassType> classTypes = service.selectAll();
        Integer code = classTypes.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = classTypes.isEmpty() ? "查詢成功" : "查無此項目";
        return new Result(code, classTypes, msg);
    }
}
