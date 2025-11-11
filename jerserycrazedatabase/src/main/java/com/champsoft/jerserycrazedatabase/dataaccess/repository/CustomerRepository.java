package com.champsoft.jerserycrazedatabase.dataaccess.repository;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}