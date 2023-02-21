package com.musclebeach.course.mapper;

import com.musclebeach.course.entity.AbsentMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AbsentMemberMapper {
    List<AbsentMember> selectAll();

    List<AbsentMember> selectByMemID(Integer memID);

    List<AbsentMember> selectByTimeID(Integer timeID);
}
