package com.musclebeach.cart.service.impl;

import com.musclebeach.cart.entity.CartItem;
import com.musclebeach.cart.entity.CartProduct;
import com.musclebeach.cart.mapper.CartProductMapper;
import com.musclebeach.cart.service.CartProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductServiceImpl implements CartProductService {
    @Resource
    private CartProductMapper cartProductMapper;

    @Override
    public List<CartProduct> getCartProduct(List<CartItem> cartItemList) {
        List<CartProduct> cartProductList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            CartProduct cartProduct = cartProductMapper.getCartProduct(cartItem.getProID());
            cartProduct.setCount(cartItem.getCount());
            cartProduct.setTotalPrice((cartItem.getCount() * cartProduct.getProPrice()));
            cartProductList.add(cartProduct);
        }
        return cartProductList;
    }
}
