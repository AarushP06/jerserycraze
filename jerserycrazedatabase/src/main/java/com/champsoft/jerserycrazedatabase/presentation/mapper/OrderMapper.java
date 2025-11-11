package com.champsoft.jerserycrazedatabase.presentation.mapper;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Order;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.OrderItem;
import com.champsoft.jerserycrazedatabase.presentation.dto.Order.OrderResponse;
import com.champsoft.jerserycrazedatabase.presentation.dto.Order.OrderResponse.OrderItemResponse;
import com.champsoft.jerserycrazedatabase.presentation.dto.Order.OrderSummaryResponse;

import java.util.Collections;
import java.util.List;

public class OrderMapper {

    public static OrderResponse toResponse(Order order) {
        Customer customer = order.getCustomer();

        List<OrderItemResponse> items = order.getOrderItems() == null
                ? Collections.emptyList()
                : order.getOrderItems().stream()
                .map(OrderMapper::toOrderItemResponse)
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderDate(),
                customer != null ? customer.getId() : null,
                customer != null ? customer.getFirstName() : null,
                customer != null ? customer.getLastName() : null,
                items
        );
    }

    private static OrderItemResponse toOrderItemResponse(OrderItem item) {
        Jersey jersey = item.getJersey();
        return new OrderItemResponse(
                jersey != null ? jersey.getId() : null,
                jersey != null ? jersey.getName() : null,
                item.getQuantity(),
                item.getUnitPrice()
        );
    }

    public static OrderSummaryResponse toSummary(Order order) {
        return new OrderSummaryResponse(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderDate()
        );
    }


}
