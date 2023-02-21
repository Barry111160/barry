package com.musclebeach.coachclassorder.model;

import java.sql.Date;
import java.util.List;

public interface CoachClassOrderDAO_interface {
    void insert(CoachClassOrderVO coachClassOrderVO);

    void update(CoachClassOrderVO coachClassOrderVO);

    void delete(Integer orderID);

    CoachClassOrderVO findByPrimaryKey(Integer orderID);

    List<CoachClassOrderVO> getAll();

    List<CoachClassOrderVO> getAllCoachPeriodByEmpAndClassTime(Integer empid, Date coachdate);

    List<CoachClassOrderVO> getAllbyMem(Integer memid);
}
