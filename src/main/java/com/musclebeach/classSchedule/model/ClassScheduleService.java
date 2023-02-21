package com.musclebeach.classSchedule.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class ClassScheduleService {
    @Resource
    private ClassScheduleIDao dao;

    public ClassScheduleService() {
        dao = new ClassScheduleDao();
    }

    public ClassScheduleVO addClassSchedule(Integer classID, Timestamp startTime, Timestamp Endtime) {
        ClassScheduleVO vo = new ClassScheduleVO();
        vo.setClassID(classID);
        vo.setStartTime(startTime);
        vo.setEndTime(Endtime);
        dao.insert(vo);
        return vo;
    }

    public ClassScheduleVO updateClassSchedule(Integer classID, Timestamp startTime, Timestamp Endtime, Integer timeID) {
        ClassScheduleVO vo = new ClassScheduleVO();
        vo.setClassID(classID);
        vo.setStartTime(startTime);
        vo.setEndTime(Endtime);
        vo.setTimeID(timeID);
        dao.update(vo);
        return vo;
    }

    public void deleteClassSchedule(Integer timeID) {
        dao.delete(timeID);
    }

    public ClassScheduleVO getOne(Integer timeID) {
        return dao.get(timeID);
    }

    public ClassScheduleVO getClass(Integer classID) {
        return dao.getClass(classID);
    }

    public ClassScheduleVO getClassCount(Integer classID) {
        return dao.getClassCount(classID);
    }

    public List<ClassScheduleVO> getAll() {

        return dao.getAll();

    }

    public List<ClassScheduleVO> getAll(Map<String, String[]> map) {
        return dao.getAll(map);
    }
}
