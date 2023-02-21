package com.musclebeach.emp.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpService {
    @Resource
    private EmpDAO_interface dao;

    public EmpVO addEmp(String password, String empName, java.sql.Date hiredate, java.sql.Date empBirthday,
                        String empPhone, String empMail, Integer empStatus, Integer coachPrice, byte[] empImg,
                        String coachContent) {

        EmpVO empVO = new EmpVO();

        empVO.setPassword(password);
        empVO.setEmpName(empName);
        empVO.setHiredate(hiredate);
        empVO.setEmpBirthday(empBirthday);
        empVO.setEmpPhone(empPhone);
        empVO.setEmpMail(empMail);
        empVO.setEmpStatus(empStatus);
        empVO.setCoachPrice(coachPrice);
        empVO.setEmpImg(empImg);
        empVO.setCoachContent(coachContent);
        dao.insert(empVO);

        return empVO;
    }

    public EmpVO updateEmp(Integer empID, String password, String empName, java.sql.Date hiredate,
                           java.sql.Date empBirthday, String empPhone, String empMail, Integer empStatus, Integer coachPrice,
                           byte[] empImg, String coachContent) {

        EmpVO empVO = new EmpVO();

        empVO.setEmpID(empID);
        empVO.setPassword(password);
        empVO.setEmpName(empName);
        empVO.setHiredate(hiredate);
        empVO.setEmpBirthday(empBirthday);
        empVO.setEmpPhone(empPhone);
        empVO.setEmpMail(empMail);
        empVO.setEmpStatus(empStatus);
        empVO.setCoachPrice(coachPrice);
        empVO.setEmpImg(empImg);
        empVO.setCoachContent(coachContent);
        dao.update(empVO);

        return empVO;
    }

    public void deleteEmp(Integer empID) {
        dao.delete(empID);
    }

    public void updatePassword(Integer empID, String password) {
        dao.updatePassword(empID, password);
    }

    public void updateStatus(Integer empID, Integer status) {
        dao.updateStatus(empID, status);
    }

    public EmpVO getOneEmp(Integer empID) {
        return dao.findByPrimaryKey(empID);
    }

    public List<EmpVO> getAll() {
        return dao.getAll();
    }

    public List<EmpVO> getEmpByName(String empName) {
        return dao.findByName(empName);
    }

    public List<EmpVO> getFunctionEmp(Integer fID) {
        return dao.getFunctionEmp(fID);
    }

    public List<EmpVO> getCoachByEmp() {
        return dao.getCoachList();
    }

    public EmpVO getNameByEmpid(Integer empID) {
        return dao.getNameByEmpid(empID);
    }
}