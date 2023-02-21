package com.musclebeach.course.mapper;

import com.musclebeach.course.entity.TeamClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamClassMapper {
    List<TeamClass> selectAll();

    TeamClass selectByID(Integer classID);

    TeamClass selectClassNameByID(Integer classID);

    List<TeamClass> selectByCondition(TeamClass teamClass);

    List<TeamClass> selectByLimit(@Param("begin") Integer begin, @Param("size") Integer size, @Param("teamClass") TeamClass teamClass);

    Integer totalCount(TeamClass teamClass);

    Integer insert(TeamClass teamClass);

    Integer update(TeamClass teamClass);

    Integer deleteByID(Integer classID);
}
