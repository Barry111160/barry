package com.musclebeach.cart.service;

import com.musclebeach.cart.entity.CartItem;
import com.musclebeach.cart.entity.CartProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("mybatis")
public interface CartProductService {
    public List<CartProduct> getCartProduct(List<CartItem> cartItemList);
}
