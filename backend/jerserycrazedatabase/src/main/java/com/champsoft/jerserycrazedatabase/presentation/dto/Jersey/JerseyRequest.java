package com.champsoft.jerserycrazedatabase.presentation.dto.Jersey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record JerseyRequest(
        @NotBlank
        String name,

        @NotBlank
        String club,

        @NotBlank
        String type,   // Home, Away, Third

        @NotBlank
        String size,   // S, M, L, XL

        @NotNull
        @Positive
        BigDecimal price,

        @NotNull
        Boolean inStock,

        @Size(max = 1000)
        String description,

        String imageLink


) {
}
