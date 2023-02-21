package com.musclebeach.product.model.dao;

import com.musclebeach.product.model.entity.Product;
import com.musclebeach.product.model.entity.ProductImg;

import java.util.List;

public interface ProductDao {
    List<Product> selectByProdType(Integer typeName);

    List<Product> selectByKeyword(String keyword);

    void updateWithProd_IMG(Product product, ProductImg productImgs);

    List<Product> getAll();

    Product getById(Integer ID);

    Integer add(Product product);

    void update(Product product);

    void delete(Integer proID);

    //同時新增圖片ID與商品 (可用在訂單主檔與明細檔一次新增成功)
    void insertWithProd_IMG(Product product, List<ProductImg> list);
}
