package com.musclebeach.cart.controller;

import com.musclebeach.cart.entity.CartItem;
import com.musclebeach.cart.entity.CartProduct;
import com.musclebeach.cart.service.CartProductService;
import com.musclebeach.cart.service.CartService;
import com.musclebeach.common.controller.Code;
import com.musclebeach.common.controller.Result;
import com.musclebeach.mem.model.MemVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Resource
    private CartService cartService;
    @Resource
    private CartProductService cartProductService;

    @GetMapping
    public String getAll(HttpServletRequest request) {
        MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
        Integer memID = -1;
        if (memVO != null) {
            memID = memVO.getMemID();
        }
        List<CartItem> allInCartByMemID = cartService.getAllInCartByMemID(memID);
        List<CartProduct> cartProductList = cartProductService.getCartProduct(allInCartByMemID);
        request.getSession().setAttribute("cartList", cartProductList);
        return "forward:/front-end/product/cart.jsp";
    }

    @PostMapping
    @ResponseBody
    public Result addInCart(@RequestBody List<CartItem> cartItems, HttpServletRequest request) {
        MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
        Integer memID = -1;
        if (memVO != null) {
            memID = memVO.getMemID();
        }
        for (CartItem cartItem : cartItems) {
            cartService.changeInCart(memID, cartItem);
        }
        return new Result(Code.CREATE_OK, cartService.getAllInCartByMemID(memID).size(), "成功");
    }

    @DeleteMapping("/{proID}")
    @ResponseBody
    public Result deleteInCart(@PathVariable Integer proID, HttpServletRequest request) {
        MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
        Integer memID = -1;
        if (memVO != null) {
            memID = memVO.getMemID();
        }
        cartService.deleteInCart(memID, proID);
        return new Result(Code.DELETE_OK, true, "成功");
    }

    @GetMapping("/cartCount")
    @ResponseBody
    public Result getCartCount(HttpServletRequest request) {
        MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
        Integer memID = -1;
        if (memVO != null) {
            memID = memVO.getMemID();
        }
        int cartCount = 0;
        if (memID != -1) {
            cartCount = cartService.getAllInCartByMemID(memID).size();
        }
        return new Result(Code.DELETE_OK, cartCount, "成功");
    }
}
