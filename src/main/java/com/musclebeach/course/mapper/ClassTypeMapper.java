package com.musclebeach.course.mapper;

import com.musclebeach.course.entity.ClassType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassTypeMapper {
    @Select("select * from class_type")
    List<ClassType> selectAll();

    @Select("select * from class_type where type_id = #{typeID}")
    ClassType selectByID(Integer typeID);

    @Insert("insert into class_type type_name value #{typeName}")
    Integer insert(String typeName);
}
