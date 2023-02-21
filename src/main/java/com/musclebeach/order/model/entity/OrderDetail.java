package com.musclebeach.order.model.entity;

import com.musclebeach.product.model.entity.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(OrderDetailId.class)
@Table(name = "order_detail")
public class OrderDetail implements Serializable {

    @Id
    @Column(name = "order_id")
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderMaster orderMaster;
    @Id
    @Column(name = "pro_id")
    private Integer proID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pro_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "det_qty")
    private Integer detQty;
    @Column(name = "det_price")
    private Integer detPrice;
}
