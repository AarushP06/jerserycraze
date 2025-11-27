package com.champsoft.jerserycrazedatabase.presentation.dto.Order;
import com.champsoft.jerserycrazedatabase.presentation.dto.Order.OrderSummaryResponse;
import java.util.List;

public record CustomerWithOrdersResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String address,
        List<OrderSummaryResponse> orders
) {
}
