package com.musclebeach.classOrder.model;

import java.util.List;

public interface ClassOrderIDao {
    void insert(ClassOrderVO obj);

    void update(ClassOrderVO obj);

    void delete(Integer pk);

    ClassOrderVO get(Integer pk);

    List<ClassOrderVO> getAll();
}
