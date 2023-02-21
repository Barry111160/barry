package com.musclebeach.course.service;

import com.musclebeach.course.entity.ClassType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("mybatis")
public interface ClassTypeService {
    List<ClassType> selectAll();

    ClassType selectByID(Integer typeID);
}
