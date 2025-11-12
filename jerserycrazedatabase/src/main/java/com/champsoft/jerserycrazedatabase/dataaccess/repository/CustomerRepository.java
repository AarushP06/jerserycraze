package com.champsoft.jerserycrazedatabase.dataaccess.repository;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByCustomerId(Long customerId);}