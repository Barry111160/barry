package com.musclebeach.empAccess.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpAccessService {
    @Resource
    private EmpAccessDAO_interface dao;


    public EmpAccessVO addEmpAccess(Integer empID, Integer FID) {

        EmpAccessVO empAccessVO = new EmpAccessVO();

        empAccessVO.setEmpID(empID);
        empAccessVO.setfID(FID);
        dao.insert(empAccessVO);

        return empAccessVO;
    }

    public void updateEmpFunc(Integer empID, Integer FID_original, Integer FID) {
        EmpAccessVO empAccessVO = new EmpAccessVO();
        empAccessVO.setEmpID(empID);
        empAccessVO.setfID(FID_original);

        dao.update(empAccessVO, FID);
    }


    public void deleteEmpAccess(Integer empID, Integer FID) {
        dao.delete(empID, FID);
    }

    public List<EmpAccessVO> getEmp(Integer empID) {
        return dao.getEmp(empID);
    }

    public List<EmpAccessVO> getFno(Integer FID) {
        return dao.getFno(FID);
    }

    public List<EmpAccessVO> getAll() {
        return dao.getAll();
    }

    public EmpAccessVO getOneAccess(Integer empID, Integer fID) {
        return dao.findByPrimaryKey(empID, fID);
    }
}
