package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.OrderRequest;
import org.example.backend.dto.OrderResponse;
import org.example.backend.entity.Order;
import org.example.backend.entity.Product;
import org.example.backend.repository.OrderRepository;
import org.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponse createOrder(OrderRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        Double totalPrice = product.getPrice() * request.getQuantity();

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        return new OrderResponse(order.getId().toString(), totalPrice);
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
