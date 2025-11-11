package com.champsoft.jerserycrazedatabase.business;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.CustomerRepository;
import com.champsoft.jerserycrazedatabase.presentation.dto.Customer.CustomerRequest;
import com.champsoft.jerserycrazedatabase.presentation.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer " + id + " not found"));
    }

    public Customer create(CustomerRequest request) {
        Customer customer = CustomerMapper.toEntity(request);
        return customerRepository.save(customer);
    }

    public Customer update(Long id, CustomerRequest request) {
        Customer customer = getById(id);
        CustomerMapper.updateEntity(customer, request);
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer " + id + " not found");
        }
        customerRepository.deleteById(id);
    }


}