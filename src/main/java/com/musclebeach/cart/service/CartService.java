package com.musclebeach.cart.service;

import com.musclebeach.cart.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    void changeInCart(Integer memID, CartItem cartItem);

    void deleteInCart(Integer memID, Integer proID);

    void deleteAllInCart(Integer memID);

    List<CartItem> getAllInCartByMemID(Integer memID);
}
