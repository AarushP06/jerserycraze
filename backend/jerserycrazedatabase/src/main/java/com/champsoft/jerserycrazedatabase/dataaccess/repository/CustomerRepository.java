package com.champsoft.jerserycrazedatabase.dataaccess.repository;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String a, String b, String c, Pageable pageable);
}
