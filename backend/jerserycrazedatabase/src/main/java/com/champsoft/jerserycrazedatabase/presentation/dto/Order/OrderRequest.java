package com.champsoft.jerserycrazedatabase.presentation.dto.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotNull Long customerId,
        @NotEmpty List<@Valid OrderItemInput> orderItems
) {
    public record OrderItemInput(
            @NotNull Long jerseyId,
            @NotNull @Min(1) Integer quantity
    ) {
    }
}
