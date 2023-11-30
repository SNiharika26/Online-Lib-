package com.example.online.lib.service;

import com.example.online.lib.entity.Book;
import com.example.online.lib.entity.Cart;
import com.example.online.lib.entity.Order;
import com.example.online.lib.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
    public class OrderService {
        @Autowired
        private OrderRepo orderRepository;

        public Order createOrder(Cart cart) {
            Order order = new Order();
            order.setBooks(cart.getItems());
            order.setTotalAmount(calculateTotalAmount(cart));
            order.setOrderDate(LocalDateTime.now());
            return orderRepository.save(order);
        }

        public List<Order> getOrders() {
            return orderRepository.findAll();
        }

        public Order getOrderById(Long orderId) {
            return orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        }

        private double calculateTotalAmount(Cart cart) {
            return cart.getItems().stream().mapToDouble(Book::getPrice).sum();
        }
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setBooks(updatedOrder.getBooks());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        return orderRepository.save(existingOrder);
    }

    public void cancelOrder(Long orderId) {
        Order existingOrder = getOrderById(orderId);
        orderRepository.delete(existingOrder);
    }
    }

