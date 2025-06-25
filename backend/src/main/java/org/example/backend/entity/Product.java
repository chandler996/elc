package org.example.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "[products]", schema = "backend")
public class Product {
    @Id
    @Column(name = "[id]")
    private String id;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[price]")
    private Double price;

    @Column(name = "[stock]")
    private Integer stock;
}
