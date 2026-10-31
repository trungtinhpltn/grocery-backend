package com.example.grocery_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name="suppliers")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String phoneNumber;
    private String address;
}
