package com.musclebeach.order.model.dao;

import com.musclebeach.order.model.entity.OrderDetail;

import java.util.List;

public interface DetailDao {

    void update(OrderDetail orderDetail);

    List<OrderDetail> findByOrderID(Integer orderID);

    List<OrderDetail> getAll();

    OrderDetail getById(Integer id);

    Integer add(OrderDetail orderDetail);

    void delete(Integer orderID, Integer prodID);
}
