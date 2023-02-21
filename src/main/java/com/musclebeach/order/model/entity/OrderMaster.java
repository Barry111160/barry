package com.musclebeach.order.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "order_master")
public class OrderMaster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderID;
    @OneToMany(mappedBy = "orderMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail;
    @Column(name = "mem_id")
    private Integer memID;
    @Column(name = "total_price")
    private Integer totalPrice;
    @Column(name = "order_rec_name")
    private String orderRecName;
    @Column(name = "order_rec_phone")
    private String orderRecPhone;
    @Column(name = "order_address")
    private String orderAddress;
    @Column(name = "order_status")
    private Integer orderStatus;
    @Column(name = "createtime", insertable = false, updatable = false)
    private Timestamp createTime;
    @Column(name = "updatetime", insertable = false)
    private Timestamp updateTime;
}
