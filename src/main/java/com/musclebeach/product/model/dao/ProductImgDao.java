package com.musclebeach.product.model.dao;

import com.musclebeach.product.model.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    void insert(ProductImg prod_IMGVO);

    void update(ProductImg prod_IMGVO);

    ProductImg findByproID(Integer proID);

    List<ProductImg> getAll();

    ProductImg getByproID(Integer proID);

    void insert2(ProductImg prod_IMGVO, java.sql.Connection conn);

    void delete(Integer proID);

    ProductImg getById(Integer ID);

    Integer add(ProductImg productImg);
}
