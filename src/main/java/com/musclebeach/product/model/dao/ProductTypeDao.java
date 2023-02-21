package com.musclebeach.product.model.dao;

import com.musclebeach.product.model.entity.ProductType;

import java.util.List;

public interface ProductTypeDao {
    
    List<ProductType> getAll();

    ProductType getById(Integer id);

    Integer add(ProductType t);
}
