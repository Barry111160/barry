package com.musclebeach.coachtime.model;

import java.sql.Date;
import java.util.List;

public interface CoachTimeDAO_interface {
    void insert(CoachTimeVO coachTimeVO);

    void update(CoachTimeVO coachTimeVO);

    void delete(Integer timeID);

    CoachTimeVO findByPrimaryKey(Integer timeID);

    List<CoachTimeVO> getAll();

    CoachTimeVO getCoachTime(Integer empid);

    CoachTimeVO getCoachDate(Integer empid);

    List<CoachTimeVO> getAllCoachDate(Integer empid);

    CoachTimeVO getCoachTimeByCoachDate(Integer empid, Date coachdate);

    List<CoachTimeVO> getAllByEmpID(Integer empid);
}
