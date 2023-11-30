package com.example.online.lib.service;

import com.example.online.lib.entity.Book;
import com.example.online.lib.entity.Cart;
import com.example.online.lib.repo.BookRepo;
import com.example.online.lib.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private BookRepo bookRepo;

    public Cart addToCart(Cart cart, Long bookId) {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            cart.getItems().add(book);
            return cartRepo.save(cart);
        } else {
            throw new RuntimeException("Book not found with id: " + bookId);
        }
    }

    public Cart removeFromCart(Cart cart, Long bookId) {
        cart.getItems().removeIf(book -> book.getId().equals(bookId));
        return cartRepo.save(cart);
    }

    public Cart getCartById(Long cartId) {
        return cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
    }

}
