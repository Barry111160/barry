package com.musclebeach.course.controller;

import com.musclebeach.common.controller.Code;
import com.musclebeach.common.controller.Result;
import com.musclebeach.course.entity.ClassSchedule;
import com.musclebeach.course.entity.PageBean;
import com.musclebeach.course.entity.TeamClass;
import com.musclebeach.course.service.ClassScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/classSchedules")
public class ClassScheduleController {
    @Resource
    private ClassScheduleService service;

    @GetMapping
    public Result selectAll() {
        List<ClassSchedule> classSchedules = service.selectAll();
        Integer code = !classSchedules.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !classSchedules.isEmpty() ? "成功" : "查無此項目";
        return new Result(code, classSchedules, msg);
    }

    @GetMapping("/classID/{classID}")
    public Result selectByClassID(@PathVariable Integer classID) {
        List<ClassSchedule> classSchedules = service.selectByClassID(classID);
        Integer code = !classSchedules.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !classSchedules.isEmpty() ? "成功" : "查無此項目";
        return new Result(code, classSchedules, msg);
    }

    @GetMapping("/page/{currentPage}/{pageSize}")
    public Result selectByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, TeamClass teamClass) {
        PageBean<ClassSchedule> classSchedulePageBean = service.selectByCurrentMonth(currentPage, pageSize, teamClass);
        Integer code = classSchedulePageBean != null ? Code.READ_OK : Code.READ_ERR;
        String msg = classSchedulePageBean != null ? "成功" : "查無此項目";
        System.out.println(teamClass);
        return new Result(code, classSchedulePageBean, msg);
    }
}
