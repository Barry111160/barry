package com.musclebeach.backstage.controller;

import com.musclebeach.backstage.entity.Member;
import com.musclebeach.backstage.service.MemberService;
import com.musclebeach.common.controller.Code;
import com.musclebeach.common.controller.Result;
import com.musclebeach.mem.model.MemVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "courseMember")
    private MemberService service;

    @GetMapping("/memID")
    public Result getMemberInfo(HttpServletRequest request) {
        MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
        Member memberInfo = null;
        if (memVO != null){
            memberInfo = service.getMemberInfo(memVO.getMemID());
        }
        Integer code = memberInfo != null ? Code.READ_OK : Code.READ_ERR;
        String msg = memberInfo != null ? "成功" : "查無此會員資訊";
        return new Result(code, memberInfo, msg);
    }
}
