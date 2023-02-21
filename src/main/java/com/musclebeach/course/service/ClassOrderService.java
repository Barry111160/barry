package com.musclebeach.course.service;

import com.musclebeach.course.entity.ClassOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("mybatis")
public interface ClassOrderService {
    List<ClassOrder> selectAll();

    ClassOrder selectByID(Integer orderID);

    List<ClassOrder> selectByMemID(Integer memID);

    Integer countByClassID(Integer classID);

    Boolean insert(ClassOrder classOrder);

    Boolean reserve(ClassOrder classOrder);

    Boolean update(ClassOrder classOrder);

    Boolean cancel(ClassOrder classOrder);
}
