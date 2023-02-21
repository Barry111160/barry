package com.musclebeach.course.mapper;

import com.musclebeach.course.entity.ClassSchedule;
import com.musclebeach.course.entity.TeamClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassScheduleMapper {

    List<ClassSchedule> selectByCurrentMonth(@Param("begin") Integer begin, @Param("size") Integer size, @Param("teamClass") TeamClass teamClass);

    List<ClassSchedule> selectAll();

    List<ClassSchedule> selectByClassID(Integer classID);

    Integer totalCount(TeamClass teamClass);

    ClassSchedule selectByID(Integer timeID);
}
