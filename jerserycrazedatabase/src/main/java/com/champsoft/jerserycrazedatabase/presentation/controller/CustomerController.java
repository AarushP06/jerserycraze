package com.champsoft.jerserycrazedatabase.presentation.controller;
import com.champsoft.jerserycrazedatabase.business.CustomerService;
import com.champsoft.jerserycrazedatabase.presentation.dto.Customer.CustomerRequest;
import com.champsoft.jerserycrazedatabase.presentation.dto.Customer.CustomerResponse;
import com.champsoft.jerserycrazedatabase.presentation.mapper.CustomerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() {
        List<CustomerResponse> body = customerService.getAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getOne(@PathVariable Long id) {
        var customer = customerService.getById(id);
        CustomerResponse body = CustomerMapper.toResponse(customer);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {
        var saved = customerService.create(request);
        CustomerResponse body = CustomerMapper.toResponse(saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id,
                                                   @RequestBody CustomerRequest request) {
        var updated = customerService.update(id, request);
        CustomerResponse body = CustomerMapper.toResponse(updated);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
