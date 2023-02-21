package com.musclebeach.course.controller;

import com.musclebeach.common.controller.Code;
import com.musclebeach.common.controller.Result;
import com.musclebeach.course.entity.PageBean;
import com.musclebeach.course.entity.TeamClass;
import com.musclebeach.course.service.TeamClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/teamClasses")
public class TeamClassController {
    @Resource
    private TeamClassService service;

    @PostMapping
    public Result insert(@RequestBody TeamClass teamClass) {
        System.out.println(teamClass);
        Boolean insert = service.insert(teamClass);
        Integer code = insert ? Code.CREATE_OK : Code.CREATE_ERR;
        String msg = insert ? "新增成功" : "新增失敗";
        return new Result(code, insert, msg);
    }

    @GetMapping("/{classID}")
    public Result selectByID(@PathVariable Integer classID) {
        TeamClass teamClass = service.selectByID(classID);
        Integer code = teamClass != null ? Code.READ_OK : Code.READ_ERR;
        String msg = teamClass != null ? "查詢成功" : "查無此項目";
        return new Result(code, teamClass, msg);
    }

    @GetMapping
    public Result selectByCondition(TeamClass teamClass) {
        List<TeamClass> teamClasses = service.selectByCondition(teamClass);
        Integer code = !teamClasses.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !teamClasses.isEmpty() ? "查詢成功" : "查無此項目";
        return new Result(code, teamClasses, msg);
    }

    @GetMapping("/page/{currentPage}/{pageSize}")
    public Result selectByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, TeamClass teamClass) {
        System.out.println(teamClass);
        PageBean<TeamClass> teamClassPageBean = service.selectByPage(currentPage, pageSize, teamClass);
        Integer code = teamClassPageBean == null ? Code.READ_OK : Code.READ_ERR;
        String msg = teamClassPageBean == null ? "查詢成功" : "查無此項目";
        return new Result(code, teamClassPageBean, msg);
    }

    @PutMapping
    public Result update(@RequestBody TeamClass teamClass) {
        Boolean update = service.update(teamClass);
        Integer code = update ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = update ? "修改成功" : "修改失敗";
        return new Result(code, update, msg);
    }

    @DeleteMapping("/{classID}")
    public Result deleteByID(@PathVariable Integer classID) {
        Boolean delete = service.deleteByID(classID);
        Integer code = delete ? Code.DELETE_OK : Code.DELETE_ERR;
        String msg = delete ? "刪除成功" : "刪除失敗";
        return new Result(code, delete, msg);
    }
}
