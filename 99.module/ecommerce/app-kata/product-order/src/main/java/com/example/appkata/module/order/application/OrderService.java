package com.example.appkata.module.order.application;

import com.example.appkata.module.order.application.dto.CreateOrderRequest;
import com.example.appkata.module.order.domain.Order;
import com.example.appkata.module.order.domain.OrderRepository;
import com.example.appkata.module.product.application.ProductService;
import com.example.appkata.module.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;

    public Order order(CreateOrderRequest request) {
        Product product = productService.findProduct(request.getProductId());
        Order order = new Order(product, request.getQuantity());
        orderRepository.save(order);
        return order;
    }

    public Order findOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return order;
    }
}
