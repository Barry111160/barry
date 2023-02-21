package com.musclebeach.product.model.dao.impl;

import com.musclebeach.product.model.dao.ProductTypeDao;
import com.musclebeach.product.model.entity.ProductType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductTypeDaoImpl implements ProductTypeDao {
    @PersistenceContext
    private Session session;

    @Override
    public List<ProductType> getAll() {
        String hql = "FROM ProductType";
        return session.createQuery(hql, ProductType.class).list();

    }

    @Override
    public ProductType getById(Integer id) {
        return session.get(ProductType.class, id);
    }

    @Override
    public Integer add(ProductType t) {
        return (Integer) session.save(t);
    }
}
