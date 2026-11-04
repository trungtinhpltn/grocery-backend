package com.example.grocery_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(example = "spadmin") String username,
        @Schema(example = "123456789") String password
) {
}