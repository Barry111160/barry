package com.musclebeach.coachtime.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class CoachTimeService {
    @Resource
    private CoachTimeDAO_interface dao;

    public CoachTimeVO addCoach(Integer empID, String coachTime, java.sql.Date coachDate) {

        CoachTimeVO coachTimeVO = new CoachTimeVO();
        coachTimeVO.setEmpID(empID);
        coachTimeVO.setCoachTime(coachTime);
        coachTimeVO.setCoachDate(coachDate);
        dao.insert(coachTimeVO);

        return coachTimeVO;
    }

    public CoachTimeVO updateEmp(Integer timeID, Integer empID, String coachTime, java.sql.Date coachDate) {

        CoachTimeVO coachTimeVO = new CoachTimeVO();

        coachTimeVO.setTimeID(timeID);
        coachTimeVO.setEmpID(empID);
        coachTimeVO.setCoachTime(coachTime);
        coachTimeVO.setCoachDate(coachDate);
//		coachClassOrderVO.getUpdateTime();
        dao.update(coachTimeVO);

        return getOneEmp(timeID);
    }

    public void deleteEmp(Integer timeID) {
        dao.delete(timeID);
    }

    public CoachTimeVO getOneEmp(Integer timeID) {
        return dao.findByPrimaryKey(timeID);
    }

    public List<CoachTimeVO> getAll() {
        return dao.getAll();
    }

    public CoachTimeVO getCoachTime(Integer empid) {
        return dao.getCoachTime(empid);
    }

    public CoachTimeVO getCoachDate(Integer empid) {
        return dao.getCoachDate(empid);
    }

    public List<CoachTimeVO> getAllCoachDate(Integer empid) {
        return dao.getAllCoachDate(empid);
    }

    public CoachTimeVO getCoachTimeByCoachDate(Integer empid, Date coachdate) {
        return dao.getCoachTimeByCoachDate(empid, coachdate);
    }

    public List<CoachTimeVO> getAllByEmpID(Integer empid) {
        return dao.getAllByEmpID(empid);
    }
}
