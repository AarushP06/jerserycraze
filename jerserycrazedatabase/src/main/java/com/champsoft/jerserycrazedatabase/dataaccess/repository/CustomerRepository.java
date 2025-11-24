package com.champsoft.jerserycrazedatabase.dataaccess.repository;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
    select c from Customer c
    where lower(c.firstName) like lower(concat('%', :q, '%'))
       or lower(c.lastName)  like lower(concat('%', :q, '%'))
       or lower(c.email)     like lower(concat('%', :q, '%'))
  """)
    Page<Customer> search(@Param("q") String q, Pageable pageable);
}