package com.musclebeach.product.model.dao.impl;

import com.musclebeach.product.model.dao.ProductImgDao;
import com.musclebeach.product.model.entity.ProductImg;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.util.List;

@Repository
public class ProductImgDaoImpl implements ProductImgDao {
    @PersistenceContext
    private Session session;

    @Override
    public void insert(ProductImg prod_IMGVO) {
        session.save(prod_IMGVO);
    }

    @Override
    public void update(ProductImg prod_IMGVO) {

    }

    @Override
    public ProductImg findByproID(Integer proID) {
        return session.get(ProductImg.class, proID);
    }

    @Override
    public List<ProductImg> getAll() {
        String hql = "FROM ProductImg";
        return session.createQuery(hql, ProductImg.class).list();

    }

    @Override
    public ProductImg getByproID(Integer proID) {
        String hql = "FROM ProductImg WHERE proID = :proID";
        return session.createQuery(hql, ProductImg.class).setParameter("proID", proID).uniqueResult();
    }

    @Override
    public void insert2(ProductImg prod_IMGVO, Connection conn) {

    }

    @Override
    public void delete(Integer proID) {
        ProductImg productImg = new ProductImg();
        productImg.setProID(proID);
        session.delete(proID);
    }

    @Override
    public ProductImg getById(Integer ID) {
        return null;
    }

    @Override
    public Integer add(ProductImg productImg) {
        return (Integer) session.save(productImg);
    }
}
