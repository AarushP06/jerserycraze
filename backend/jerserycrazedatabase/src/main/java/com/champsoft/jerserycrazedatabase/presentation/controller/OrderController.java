package com.champsoft.jerserycrazedatabase.presentation.controller;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Order;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    // list all orders
    @GetMapping("/orders")
    public List<Order> all() {
        return repo.findAll();
    }

    // list orders by customer
    @GetMapping("/customers/{customerId}/orders")
    public List<Order> byCustomer(@PathVariable Long customerId) {
        return repo.findByCustomerId(customerId);
    }

    // create
    @PostMapping("/orders")
    public Order create(@RequestBody Order o) {
        return repo.save(o);
    }

    // update
    @PutMapping("/orders/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order o) {
        o.setId(id);
        return repo.save(o);
    }

    // delete
    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
