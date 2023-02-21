package com.musclebeach.product.model.service;

import com.musclebeach.product.model.entity.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> getAll();

    ProductType getOneProdType(Integer prodTypeID);

    ProductType addProdType(String typeName);
}
