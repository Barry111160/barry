package com.musclebeach.backstage.mapper;

import com.musclebeach.backstage.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("select emp_id, emp_name from employee where emp_id = #{empID}")
    Employee selectEmpNameByID(Integer empID);
}
