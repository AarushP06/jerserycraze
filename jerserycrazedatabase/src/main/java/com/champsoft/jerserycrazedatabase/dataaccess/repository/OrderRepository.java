package com.champsoft.jerserycrazedatabase.dataaccess.repository;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
