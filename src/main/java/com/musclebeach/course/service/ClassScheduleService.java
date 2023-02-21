package com.musclebeach.course.service;

import com.musclebeach.course.entity.ClassSchedule;
import com.musclebeach.course.entity.PageBean;
import com.musclebeach.course.entity.TeamClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("mybatis")
public interface ClassScheduleService {
    List<ClassSchedule> selectAll();

    PageBean<ClassSchedule> selectByCurrentMonth(Integer currentPage, Integer pageSize, TeamClass teamClass);

    List<ClassSchedule> selectByClassID(Integer classID);

}
