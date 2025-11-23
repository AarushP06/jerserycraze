package com.champsoft.jerserycrazedatabase.presentation.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderSummaryResponse(
        Long id,
        String status,
        BigDecimal totalAmount,
        LocalDate orderDate
) {
}
