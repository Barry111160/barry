package com.musclebeach.course.service;

import com.musclebeach.course.entity.PageBean;
import com.musclebeach.course.entity.TeamClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("mybatis")
public interface TeamClassService {
    List<TeamClass> selectAll();

    TeamClass selectByID(Integer classID);

    List<TeamClass> selectByCondition(TeamClass teamClass);

    PageBean<TeamClass> selectByPage(Integer currentPage, Integer pageSize, TeamClass teamClass);

    Boolean insert(TeamClass teamClass);

    Boolean update(TeamClass teamClass);

    Boolean deleteByID(Integer classID);
}
