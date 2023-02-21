package com.musclebeach.product.model.service;

import com.musclebeach.product.model.entity.ProductImg;

public interface ProductImgService {
    ProductImg getOneproID(Integer proID);

    ProductImg insertImg(Integer proID, byte[] proImg);
}
