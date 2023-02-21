package com.musclebeach.course.service.impl;

import com.musclebeach.course.entity.AbsentMember;
import com.musclebeach.course.mapper.AbsentMemberMapper;
import com.musclebeach.course.service.AbsentMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AbsentMemberServiceImpl implements AbsentMemberService {
    @Resource
    private AbsentMemberMapper mapper;

    @Override
    public List<AbsentMember> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<AbsentMember> selectByMemID(Integer memID) {
        return mapper.selectByMemID(memID);
    }

    @Override
    public List<AbsentMember> selectByTimeID(Integer timeID) {
        return mapper.selectByTimeID(timeID);
    }
}
