package com.musclebeach.empAccess.model;

import java.util.List;


public interface EmpAccessDAO_interface {
    void insert(EmpAccessVO empAccessVO);

    void delete(Integer empID, Integer fID);

    void update(EmpAccessVO empAccessVO, Integer fID);

    List<EmpAccessVO> getEmp(Integer empID);

    List<EmpAccessVO> getFno(Integer fID);

    List<EmpAccessVO> getAll();

    EmpAccessVO findByPrimaryKey(Integer empID, Integer fID);
}
