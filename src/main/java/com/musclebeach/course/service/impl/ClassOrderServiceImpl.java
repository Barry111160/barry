package com.musclebeach.course.service.impl;

import com.musclebeach.course.entity.ClassOrder;
import com.musclebeach.course.mapper.ClassOrderMapper;
import com.musclebeach.course.service.ClassOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassOrderServiceImpl implements ClassOrderService {
    @Resource
    private ClassOrderMapper mapper;

    @Override
    public List<ClassOrder> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public ClassOrder selectByID(Integer orderID) {
        return mapper.selectByID(orderID);
    }

    @Override
    public List<ClassOrder> selectByMemID(Integer memID) {
        return mapper.selectByMemID(memID);
    }

    @Override
    public Integer countByClassID(Integer classID) {
        return mapper.countByClassID(classID);
    }

    @Override
    public Boolean insert(ClassOrder classOrder) {
        return mapper.insert(classOrder) > 0;
    }

    @Override
    public Boolean reserve(ClassOrder classOrder) {
        Integer classID = classOrder.getTeamClass().getClassID();
        Integer totalCount = mapper.countByClassID(classID);
        Integer peopleMax = mapper.getPeopleMaxByClassID(classID);
        ClassOrder existOrder = mapper.selectByCondition(classOrder);
        if (totalCount >= peopleMax) {
            return false;
        }
        if (existOrder != null) {
            if (existOrder.getOrderStatus() == 1) {
                return false;
            }
            existOrder.setOrderStatus(1);
            return update(existOrder);
        }
        return insert(classOrder);
    }

    @Override
    public Boolean update(ClassOrder classOrder) {
        return mapper.update(classOrder) > 0;
    }

    @Override
    public Boolean cancel(ClassOrder classOrder) {
        ClassOrder co = mapper.selectByCondition(classOrder);
        co.setOrderStatus(0);
        return mapper.update(co) > 0;
    }
}
