package com.champsoft.jerserycrazedatabase.presentation.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        Long id,
        String status,
        BigDecimal totalAmount,
        LocalDate orderDate,
        Long customerId,
        String customerFirstName,
        String customerLastName,
        List<OrderItemResponse> items
) {
    public record OrderItemResponse(
            Long jerseyId,
            String jerseyName,
            Integer quantity,
            BigDecimal unitPrice
    ) {
    }
}
