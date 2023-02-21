package com.musclebeach.order.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    private Integer orderID;
    private Integer proID;
}
