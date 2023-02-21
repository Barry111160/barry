package com.musclebeach.course.service.impl;

import com.musclebeach.course.entity.ClassType;
import com.musclebeach.course.mapper.ClassTypeMapper;
import com.musclebeach.course.service.ClassTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassTypeServiceImpl implements ClassTypeService {
    @Resource
    private ClassTypeMapper mapper;

    @Override
    public List<ClassType> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public ClassType selectByID(Integer typeID) {
        return mapper.selectByID(typeID);
    }
}
