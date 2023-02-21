package com.musclebeach.order.model.dao.impl;

import com.musclebeach.order.model.dao.MasterDao;
import com.musclebeach.order.model.entity.OrderDetail;
import com.musclebeach.order.model.entity.OrderMaster;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional("hibernate")
public class MasterDaoImpl implements MasterDao {
    @PersistenceContext
    private Session session;

    @Override
    public OrderMaster getById(Integer id) {
        return session.get(OrderMaster.class, id);
    }

    @Override
    public Integer add(OrderMaster orderMaster) {
        session.persist(orderMaster);
        return orderMaster.getOrderID();
    }

    @Override
    public void update(OrderMaster orderMaster) {
        String sql = "update order_master om set om.total_price = ?, om.order_rec_name = ?, om.order_rec_phone = ?"
                + ", om.order_address = ?, om.order_status = ? where om.order_id = ?";
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, orderMaster.getTotalPrice());
        query.setParameter(2, orderMaster.getOrderRecName());
        query.setParameter(3, orderMaster.getOrderRecPhone());
        query.setParameter(4, orderMaster.getOrderAddress());
        query.setParameter(5, orderMaster.getOrderStatus());
        query.executeUpdate();
    }

    @Override//改變訂單狀態
    public void updateStatus(OrderMaster orderMaster) {
        String sql = "update order_master om set om.order_status = ? where om.order_id = ?";
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, orderMaster.getOrderStatus());
        query.setParameter(2, orderMaster.getOrderID());
        query.executeUpdate();
    }

    @Override
    public void delete(Integer orderID) {
        Query query = session.createSQLQuery("delete from order_master where order_id = ?");
        query.setParameter(1, orderID);
        query.executeUpdate();
    }

    @Override
    public List<OrderMaster> getAll() {
        String hql = "from OrderMaster order by createTime desc";
        return session.createQuery(hql, OrderMaster.class).list();
    }

    @Override
    public void insertWithDetail(OrderMaster orderMaster, OrderDetail orderDetail) {
        session.save(orderMaster);
        session.save(orderDetail);
    }

    @Override
    public List<OrderMaster> findByMemID(Integer memID) {
        String hql = "FROM OrderMaster WHERE memID = :memID order by createTime desc";
        return session.createQuery(hql, OrderMaster.class).
                setParameter("memID", memID)
                .list();
    }
}
