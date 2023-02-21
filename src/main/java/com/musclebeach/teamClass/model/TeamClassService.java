package com.musclebeach.teamClass.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeamClassService {
    @Resource
    private TeamClassIDao dao;
    
    public TeamClassVO addTeamClass(Integer empID, Integer typeID, String className, Integer peopleMax, String classContent, Integer classStatus) {
        TeamClassVO vo = new TeamClassVO();
        vo.setEmpID(empID);
        vo.setTypeID(typeID);
        vo.setClassName(className);
        vo.setPeopleMax(peopleMax);
        vo.setClassContent(classContent);
        vo.setClassStatus(classStatus);
        dao.insert(vo);
        return vo;
    }

    public TeamClassVO updateTeamClass(Integer classID, Integer empID, Integer typeID, String className, Integer peopleMax, String classContent, Integer classStatus) {
        TeamClassVO vo = new TeamClassVO();
        vo.setClassID(classID);
        vo.setEmpID(empID);
        vo.setTypeID(typeID);
        vo.setClassName(className);
        vo.setPeopleMax(peopleMax);
        vo.setClassContent(classContent);
        vo.setClassStatus(classStatus);
        dao.update(vo);
        return vo;
    }

    public void deleteTeamClass(Integer classID) {
        dao.delete(classID);

    }

    public TeamClassVO getOne(Integer classID) {
        return dao.get(classID);
    }

    public TeamClassVO getType(Integer typeID) {
        return dao.get(typeID);
    }

    public List<TeamClassVO> getAll() {
        return dao.getAll();

    }

    public List<TeamClassVO> getAllClassForOneType(Integer typeID){
        return dao.getClass(typeID);
    }
}
