package com.musclebeach.order.model.service;

import com.musclebeach.order.model.dao.DetailDao;
import com.musclebeach.order.model.entity.OrderDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional("hibernate")
public class DetailService {
    @Resource
    private DetailDao dao;


    public Integer add(OrderDetail orderDetail) {
        return dao.add(orderDetail);
    }

    public void update(OrderDetail orderDetail) {
        dao.update(orderDetail);
    }

    public void delete(Integer orderID, Integer prodID) {
        dao.delete(orderID, prodID);
    }

    public OrderDetail getByID(Integer id) {
        return dao.getById(id);
    }

    public List<OrderDetail> getAll() {
        return dao.getAll();
    }
}
