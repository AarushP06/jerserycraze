package com.champsoft.jerserycrazedatabase.presentation.controller;
import com.champsoft.jerserycrazedatabase.business.OrderService;
import com.champsoft.jerserycrazedatabase.presentation.dto.Order.OrderRequest;
import com.champsoft.jerserycrazedatabase.presentation.dto.Order.OrderResponse;
import com.champsoft.jerserycrazedatabase.presentation.mapper.OrderMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> body = orderService.getAll()
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOne(@PathVariable Long id) {
        var order = orderService.getById(id);
        OrderResponse body = OrderMapper.toResponse(order);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
        var saved = orderService.create(request);
        OrderResponse body = OrderMapper.toResponse(saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id,
                                                @RequestBody OrderRequest request) {
        var updated = orderService.update(id, request);
        OrderResponse body = OrderMapper.toResponse(updated);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
