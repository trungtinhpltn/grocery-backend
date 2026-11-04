package com.example.grocery_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Name is not blank")
    private String name;
    private String description;
}
