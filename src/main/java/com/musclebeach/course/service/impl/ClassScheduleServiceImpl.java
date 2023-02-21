package com.musclebeach.course.service.impl;

import com.musclebeach.course.entity.ClassSchedule;
import com.musclebeach.course.entity.PageBean;
import com.musclebeach.course.entity.TeamClass;
import com.musclebeach.course.mapper.ClassScheduleMapper;
import com.musclebeach.course.service.ClassScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassScheduleServiceImpl implements ClassScheduleService {
    @Resource
    private ClassScheduleMapper mapper;

    @Override
    public List<ClassSchedule> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageBean<ClassSchedule> selectByCurrentMonth(Integer currentPage, Integer pageSize, TeamClass teamClass) {
        Integer begin = (currentPage - 1) * pageSize;
        List<ClassSchedule> classSchedules = mapper.selectByCurrentMonth(begin, pageSize, teamClass);
        Integer totalCount = mapper.totalCount(teamClass);
        PageBean<ClassSchedule> classSchedulePageBean = new PageBean<>();
        classSchedulePageBean.setTotalCount(totalCount);
        classSchedulePageBean.setList(classSchedules);
        return classSchedulePageBean;
    }

    @Override
    public List<ClassSchedule> selectByClassID(Integer classID) {
        return mapper.selectByClassID(classID);
    }


}
