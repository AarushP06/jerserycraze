package com.champsoft.jerserycrazedatabase.dataaccess.repository;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}