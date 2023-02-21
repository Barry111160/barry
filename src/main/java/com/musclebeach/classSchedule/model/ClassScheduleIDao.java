package com.musclebeach.classSchedule.model;

import java.util.List;
import java.util.Map;


public interface ClassScheduleIDao {
    void insert(ClassScheduleVO obj);

    void update(ClassScheduleVO obj);

    void delete(Integer pk);

    ClassScheduleVO getClassCount(Integer classID);

    ClassScheduleVO getClass(Integer classID);

    ClassScheduleVO get(Integer pk);

    List<ClassScheduleVO> getAll();

    List<ClassScheduleVO> getAll(Map<String, String[]> map);
}
