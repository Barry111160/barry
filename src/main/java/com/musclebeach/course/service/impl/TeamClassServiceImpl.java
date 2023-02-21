package com.musclebeach.course.service.impl;

import com.musclebeach.course.entity.PageBean;
import com.musclebeach.course.entity.TeamClass;
import com.musclebeach.course.mapper.TeamClassMapper;
import com.musclebeach.course.service.TeamClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TeamClassServiceImpl implements TeamClassService {
    @Resource
    private TeamClassMapper mapper;

    @Override
    public List<TeamClass> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public TeamClass selectByID(Integer classID) {
        return mapper.selectByID(classID);
    }

    @Override
    public List<TeamClass> selectByCondition(TeamClass teamClass) {
        return mapper.selectByCondition(teamClass);
    }

    @Override
    public PageBean<TeamClass> selectByPage(Integer currentPage, Integer pageSize, TeamClass teamClass) {
        Integer begin = (currentPage - 1) * pageSize;
        List<TeamClass> teamClasses = mapper.selectByLimit(begin, pageSize, teamClass);
        Integer totalClass = mapper.totalCount(teamClass);
        PageBean<TeamClass> teamClassPageBean = new PageBean<>();
        teamClassPageBean.setList(teamClasses);
        teamClassPageBean.setTotalCount(totalClass);
        return teamClassPageBean;
    }

    @Override
    public Boolean insert(TeamClass teamClass) {
        return mapper.insert(teamClass) > 0;
    }

    @Override
    public Boolean update(TeamClass teamClass) {
        return mapper.update(teamClass) > 0;
    }

    @Override
    public Boolean deleteByID(Integer classID) {
        return mapper.deleteByID(classID) > 0;
    }
}
