package com.champsoft.jerserycrazedatabase.business;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Order;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.CustomerRepository;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.OrderRepository;
import com.champsoft.jerserycrazedatabase.presentation.dto.Order.OrderRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order " + id + " not found"));
    }

    public Order create(OrderRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer " + request.customerId() + " not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setStatus("PENDING");
        order.setTotalAmount(BigDecimal.ZERO); // you can compute real total later

        return orderRepository.save(order);
    }

    public Order update(Long id, OrderRequest request) {
        Order order = getById(id);

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer " + request.customerId() + " not found"));

        // full update: set customer, keep date and maybe status
        order.setCustomer(customer);

        // if you want, recompute total here later
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order " + id + " not found");
        }
        orderRepository.deleteById(id);
    }


}
