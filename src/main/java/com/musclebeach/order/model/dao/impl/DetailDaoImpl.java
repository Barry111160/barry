package com.musclebeach.order.model.dao.impl;

import com.musclebeach.order.model.dao.DetailDao;
import com.musclebeach.order.model.entity.OrderDetail;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional("hibernate")
public class DetailDaoImpl implements DetailDao {
    @PersistenceContext
    private Session session;

    @Override
    public List<OrderDetail> getAll() {
        String hql = "FROM OrderDetail";
        return session.createQuery(hql, OrderDetail.class).list();
    }

    @Override
    public OrderDetail getById(Integer id) {
        return session.get(OrderDetail.class, id);
    }

    @Override
    public Integer add(OrderDetail orderDetail) {
        session.save(orderDetail);
        return orderDetail.getProID();
    }

    @Override
    public void delete(Integer orderID, Integer prodID) {

    }

    @Override
    public void update(OrderDetail orderDetail) {
        String sql = "update order_detail od set od.det_qty = ?, od.det_price = ? where od.order_id = ?";
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, orderDetail.getDetQty());
        query.setParameter(2, orderDetail.getDetPrice());
        query.executeUpdate();
    }


    @Override
    public List<OrderDetail> findByOrderID(Integer orderID) {
        String hql = "FROM OrderDetail WHERE orderID = :orderID";
        return session.createQuery(hql, OrderDetail.class).
                setParameter("orderID", orderID)
                .list();
    }

}
