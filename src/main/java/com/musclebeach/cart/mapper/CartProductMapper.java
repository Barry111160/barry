package com.musclebeach.cart.mapper;

import com.musclebeach.cart.entity.CartProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CartProductMapper {
    @Select("select pro_id, pro_name, pro_price from product where pro_id = #{proID}")
    public CartProduct getCartProduct(Integer proID);
}
