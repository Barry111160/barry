package com.musclebeach.product.model.service;

import com.musclebeach.product.model.entity.Product;
import com.musclebeach.product.model.entity.ProductImg;

import java.util.List;

public interface ProductService {
    Product getOneProd(Integer proID);

    ProductImg getOneProdIMG(Integer proID);

    List<Product> getAll();

    void deleteProd(Integer proID);

    Product updateProd(Integer proID, String proName, Integer typeID, Integer proQty, Integer proPrice,
                       String proContent, Integer proStatus, byte[] proImg);

    ProductImg upload(byte[] proImg, Integer proID);

    Product addProdAndFile(String proName, Integer typeID, Integer proQty, Integer proPrice,
                           String proContent, byte[] proImg);

    List<Product> findAllProduct();

    List<Product> findSameCategoryProduct(Integer proType);

    List<Product> findSpecifiedProduct(String keyword);

}
