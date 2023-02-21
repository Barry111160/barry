package com.musclebeach.product.model.service.impl;

import com.musclebeach.product.model.dao.ProductImgDao;
import com.musclebeach.product.model.entity.ProductImg;
import com.musclebeach.product.model.service.ProductImgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional("hibernate")
public class ProductImgServiceImpl implements ProductImgService {
    @Resource
    private ProductImgDao productImgDao;

    @Override
    public ProductImg getOneproID(Integer proID) {
        return productImgDao.getById(proID);
    }

    @Override
    public ProductImg insertImg(Integer proID, byte[] proImg) {
        ProductImg productImg = new ProductImg();

        productImg.setProImg(proImg);
        productImg.setProID(proID);
        productImgDao.add(productImg);

        return productImg;
    }
}
