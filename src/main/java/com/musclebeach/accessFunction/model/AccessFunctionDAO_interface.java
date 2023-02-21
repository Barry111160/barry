package com.musclebeach.accessFunction.model;

import java.util.List;

public interface AccessFunctionDAO_interface {
    void insert(AccessFunctionVO accessFunctionVO);

    void delete(Integer fID);

    AccessFunctionVO findByPrimaryKey(Integer fID);

    List<AccessFunctionVO> getAll();
}
