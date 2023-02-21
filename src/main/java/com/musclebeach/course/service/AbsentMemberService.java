package com.musclebeach.course.service;

import com.musclebeach.course.entity.AbsentMember;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("mybatis")
public interface AbsentMemberService {
    List<AbsentMember> selectAll();

    List<AbsentMember> selectByMemID(Integer memID);

    List<AbsentMember> selectByTimeID(Integer timeID);
}
