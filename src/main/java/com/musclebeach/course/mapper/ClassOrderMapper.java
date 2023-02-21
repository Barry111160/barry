package com.musclebeach.course.mapper;

import com.musclebeach.course.entity.ClassOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassOrderMapper {

    List<ClassOrder> selectAll();

    ClassOrder selectByID(Integer orderID);

    List<ClassOrder> selectByMemID(Integer memID);

    ClassOrder selectByCondition(ClassOrder classOrder);

    Integer countByClassID(Integer classID);

    Integer getPeopleMaxByClassID(Integer classID);

    Integer isReserved(@Param("classID") Integer classID, @Param("memID") Integer memID);

    Integer insert(ClassOrder classOrder);

    Integer update(ClassOrder classOrder);
}
