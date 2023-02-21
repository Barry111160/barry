package com.musclebeach.emp.model;

import java.util.List;


public interface EmpDAO_interface {
    void insert(EmpVO empVO);

    void update(EmpVO empVO);

    void updatePassword(Integer empID, String password);

    void delete(Integer empID);

    EmpVO findByPrimaryKey(Integer empID);

    List<EmpVO> getAll();

    List<EmpVO> findByName(String empName);

    List<EmpVO> getFunctionEmp(Integer fID);

    void updateStatus(Integer empID, Integer Status);

    EmpVO getNameByEmpid(Integer empID);

    List<EmpVO> getCoachList();
}
