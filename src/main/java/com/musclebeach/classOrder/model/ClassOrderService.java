package com.musclebeach.classOrder.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassOrderService {
    @Resource
    private ClassOrderIDao dao;

    public ClassOrderVO addClassOrder(Integer empID, Integer memID, Integer classID, Integer orderStatus) {
        ClassOrderVO vo = new ClassOrderVO();
        vo.setEmpID(empID);
        vo.setMemID(memID);
        vo.setClassID(classID);
        vo.setOrderStatus(orderStatus);
        dao.insert(vo);
        return vo;

    }

    public ClassOrderVO updateClassOrder(Integer orderID, Integer empID, Integer memID, Integer classID, Integer orderStatus) {
        ClassOrderVO vo = new ClassOrderVO();
        vo.setOrderID(orderID);
        vo.setEmpID(empID);
        vo.setMemID(memID);
        vo.setClassID(classID);
        vo.setOrderStatus(orderStatus);
        dao.update(vo);
        return vo;
    }

    public void deleteClassOrder(Integer orderID) {
        dao.delete(orderID);
    }

    public ClassOrderVO getOne(Integer orderID) {
        return dao.get(orderID);
    }

    public List<ClassOrderVO> getAll() {
        return dao.getAll();
    }
}
