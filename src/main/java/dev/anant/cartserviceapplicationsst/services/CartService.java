package dev.anant.cartserviceapplicationsst.services;

import dev.anant.cartserviceapplicationsst.models.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Cart getSingleCart(Long id);

    List<Cart> getCartsByUserId(Long userId);

    List<Cart> getCartsByDateRange(String startdate, String enddate);

    Cart createCart(Cart cart);

    void UpdateCart(Long id);

    void deleteCart(Long id);
}