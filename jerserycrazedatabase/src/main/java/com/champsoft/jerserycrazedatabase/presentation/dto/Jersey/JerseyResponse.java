package com.champsoft.jerserycrazedatabase.presentation.dto.Jersey;

import java.math.BigDecimal;

public record JerseyResponse(
        Long id,
        String name,
        String club,
        String type,
        String size,
        BigDecimal price,
        Boolean inStock,
        String description,
        String imageLink
) {
}
