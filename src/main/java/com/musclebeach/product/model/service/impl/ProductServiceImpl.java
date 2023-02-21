package com.musclebeach.product.model.service.impl;

import com.musclebeach.product.model.dao.ProductDao;
import com.musclebeach.product.model.dao.ProductImgDao;
import com.musclebeach.product.model.entity.Product;
import com.musclebeach.product.model.entity.ProductImg;
import com.musclebeach.product.model.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional("hibernate")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;
    @Resource
    private ProductImgDao productImgDao;

    @Override
    public Product getOneProd(Integer proID) {
        return productDao.getById(proID);
    }

    @Override
    public ProductImg getOneProdIMG(Integer proID) {
        return productImgDao.findByproID(proID);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }


    @Override
    public void deleteProd(Integer proID) {
        productDao.delete(proID);

    }

    @Override
    public Product updateProd(Integer proID, String proName, Integer typeID, Integer proQty, Integer proPrice,
                              String proContent, Integer proStatus, byte[] proImg) {

        Product product = new Product();

        product.setProID(proID);
        product.setProName(proName);
        product.setTypeID(typeID);
        product.setProQty(proQty);
        product.setProPrice(proPrice);
        product.setProContent(proContent);
        product.setProStatus(proStatus);

        ProductImg productImg = new ProductImg();
//		prodIMG.setproID(product.getproID());
        productImg.setProImg(proImg);

//		List<ProductImg> alist = new ArrayList<ProductImg>();
//		alist.add(prodIMG);

//		product.setUpdateImg(productImg);

        productDao.updateWithProd_IMG(product, productImg);
        return product;
    }

    @Override
    public ProductImg upload(byte[] proImg, Integer proID) {

        ProductImg prod_IMGVO = new ProductImg();

        prod_IMGVO.setProImg(proImg);
        prod_IMGVO.setProID(proID);
        productImgDao.insert(prod_IMGVO);
        return prod_IMGVO;
    }

    @Override
    public Product addProdAndFile(String proName, Integer typeID, Integer proQty, Integer proPrice,
                                  String proContent, byte[] proImg) {

        Product prodVO = new Product();
        prodVO.setProName(proName);
        prodVO.setTypeID(typeID);
        prodVO.setProQty(proQty);
        prodVO.setProPrice(proPrice);
        prodVO.setProContent(proContent);

        ProductImg prodIMG = new ProductImg();

        prodIMG.setProImg(proImg);
        List<ProductImg> alist = new ArrayList<ProductImg>();
        alist.add(prodIMG);

        productDao.insertWithProd_IMG(prodVO, alist);
        return prodVO;

    }

    @Override
    public List<Product> findAllProduct() {
        return productDao.getAll();
    }

    @Override
    public List<Product> findSameCategoryProduct(Integer prodType) {
        return productDao.selectByProdType(prodType);
    }

    @Override
    public List<Product> findSpecifiedProduct(String keyword) {
        return productDao.selectByKeyword(keyword);
    }


}
