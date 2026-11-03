package com.example.grocery_backend.requestdto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String barcode;
    private BigDecimal importPrice;
    private BigDecimal sellingPrice;
    private Integer stockQuantity;
    private Integer minStockQuantity;
    private String unit;
    private Long categoryId;

}
