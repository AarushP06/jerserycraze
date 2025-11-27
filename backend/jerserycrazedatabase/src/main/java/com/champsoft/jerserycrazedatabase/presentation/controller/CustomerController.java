// src/main/java/com/champsoft/jerserycrazedatabase/presentation/controller/CustomerController.java
package com.champsoft.jerserycrazedatabase.presentation.controller;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo){ this.repo = repo; }

    @GetMapping
    public Page<Customer> list(
            @RequestParam(required = false) String q,
            Pageable pageable
    ) {
        Pageable sorted = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").ascending()
        );

        if (q == null || q.isBlank()) {
            return repo.findAll(sorted);
        }

        String s = q.trim();
        return repo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                s, s, s, sorted
        );
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }

    @PostMapping
    public Customer create(@RequestBody Customer c){ return repo.save(c); }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer c){
        c.setId(id);
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
