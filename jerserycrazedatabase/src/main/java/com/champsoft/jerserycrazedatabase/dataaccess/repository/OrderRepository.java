package com.champsoft.jerserycrazedatabase.dataaccess.repository;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // For GET /api/customers/{id}/orders
    List<Order> findByCustomerId(Long customerId);
}
