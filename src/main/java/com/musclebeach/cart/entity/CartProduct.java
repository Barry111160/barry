package com.musclebeach.cart.entity;

import lombok.Data;

@Data
public class CartProduct {
    private Integer proID;
    private String proName;
    private Integer proPrice;
    private Integer count;
    private Integer totalPrice;
}
