package com.example.online.lib.controler;

import com.example.online.lib.entity.Cart;
import com.example.online.lib.entity.Order;
import com.example.online.lib.service.CartService;
import com.example.online.lib.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @PostMapping("/add/{bookId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long bookId, @RequestBody Cart cart) {
        try {
            Cart updatedCart = cartService.addToCart(cart, bookId);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        }
    }

    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long bookId, @RequestBody Cart cart) {
        try {
            Cart updatedCart = cartService.removeFromCart(cart, bookId);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (CartNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        }
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCartById(cartId);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/checkout/{cartId}")
    public ResponseEntity<Order> checkoutCart(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCartById(cartId);
            Order order = orderService.createOrder(cart);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
