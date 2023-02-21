package com.musclebeach.product.model.service.impl;

import com.musclebeach.product.model.dao.ProductTypeDao;
import com.musclebeach.product.model.entity.ProductType;
import com.musclebeach.product.model.service.ProductTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional("hibernate")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Resource
    private ProductTypeDao productTypeDao;

    @Override
    public List<ProductType> getAll() {
        return productTypeDao.getAll();
    }

    @Override
    public ProductType getOneProdType(Integer prodTypeID) {
        return productTypeDao.getById(prodTypeID);
    }

    @Override
    public ProductType addProdType(String typeName) {

        ProductType productType = new ProductType();

        productType.setTypeName(typeName);
        productTypeDao.add(productType);

        return productType;
    }
}
