package com.musclebeach.product.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_img")
@Data
@ToString
public class ProductImg implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Integer imgID;
    @Column(name = "pro_img")
    private byte[] proImg;
    @ManyToOne
    @JoinColumn(name = "pro_id", insertable = false, updatable = false)
    private Product product;
    @Column(name = "pro_id")
    private Integer proID;
}
