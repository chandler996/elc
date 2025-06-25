package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.entity.Product;
import org.example.backend.service.OrderService;
import org.example.backend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        var lst = productService.getAllProducts();
        return lst;
    }
}
