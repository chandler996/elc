package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders", schema = "backend")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    private Long id;

    @Column(name = "[productId]")
    private String productId;

    @Column(name = "[quantity]")
    private Integer quantity;

    @Column(name = "[totalPrice]")
    private Double totalPrice;
}
