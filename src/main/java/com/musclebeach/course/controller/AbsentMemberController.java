package com.musclebeach.course.controller;

import com.musclebeach.common.controller.Code;
import com.musclebeach.common.controller.Result;
import com.musclebeach.course.entity.AbsentMember;
import com.musclebeach.course.service.AbsentMemberService;
import com.musclebeach.mem.model.MemVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/absentMembers")
public class AbsentMemberController {
    @Resource
    private AbsentMemberService service;

    @GetMapping
    public Result selectAll() {
        List<AbsentMember> absentMembers = service.selectAll();
        Integer code = !absentMembers.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !absentMembers.isEmpty() ? "查詢成功" : "查無此項目";
        return new Result(code, absentMembers, msg);
    }

    @GetMapping("/memID")
    public Result selectByMemID(HttpServletRequest request) {
        MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
        Integer memID = -1;
        if (memVO != null) {
            memID = memVO.getMemID();
        }
        List<AbsentMember> absentMembers = service.selectByMemID(memID);
        Integer code = !absentMembers.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !absentMembers.isEmpty() ? "查詢成功" : "查無此項目";
        return new Result(code, absentMembers, msg);
    }

    @GetMapping("/timeID/{timeID}")
    public Result selectByTimeID(@PathVariable Integer timeID) {
        List<AbsentMember> absentMembers = service.selectByTimeID(timeID);
        Integer code = !absentMembers.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !absentMembers.isEmpty() ? "查詢成功" : "查無此項目";
        return new Result(code, absentMembers, msg);
    }
}
