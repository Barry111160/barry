package com.musclebeach.order.model.dao;

import com.musclebeach.order.model.entity.OrderDetail;
import com.musclebeach.order.model.entity.OrderMaster;

import java.util.List;

public interface MasterDao {
    void delete(Integer orderID);

    List<OrderMaster> getAll();

    OrderMaster getById(Integer ID);

    Integer add(OrderMaster orderMaster);

    void update(OrderMaster orderMaster);

    void updateStatus(OrderMaster orderMaster);

    //同時新增訂單主檔與明細檔
    void insertWithDetail(OrderMaster orderVO, OrderDetail detailVO);

    List<OrderMaster> findByMemID(Integer memID);
}
