package dev.anant.cartserviceapplicationsst.controllers;

import dev.anant.cartserviceapplicationsst.models.Cart;
import dev.anant.cartserviceapplicationsst.services.CartService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private CartService cartService;

    public CartController(@Qualifier("FakeStoreCartService") CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/carts/{id}")
    Cart getSingleCart(@PathVariable ("id") Long id){
        return cartService.getSingleCart(id);
    }

    @GetMapping("/carts/user/{userId}")
    // get cart by user id
    List<Cart> getCartsByUserId(@PathVariable("userId") Long userId){
        return cartService.getCartsByUserId(userId);
    }

    // get carts in a date range
    @GetMapping("/carts?startdate={startDate}&enddate={endDate}")
    List<Cart> getCartsByDateRange(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate){
        return cartService.getCartsByDateRange(startDate, endDate);
    }

    // create/add a new cart
    @PostMapping ("/carts")
    Cart createCart(@RequestBody Cart cart){
        return cartService.createCart(cart);
    }

    // update a cart
    @PutMapping("/carts/{id}")
    void updateCart(@RequestBody Cart cart, @PathVariable("id") Long id){
        cartService.UpdateCart(id);
    }

    @DeleteMapping("/carts/{id}")
    void deleteCart(@PathVariable("id") Long id){
        cartService.deleteCart(id);
    }

}
