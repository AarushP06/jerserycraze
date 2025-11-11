package com.champsoft.jerserycrazedatabase.business;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Customer;
import com.champsoft.jerserycrazedatabase.presentation.dto.Customer.CustomerRequest;

public class CustomerService {
    public Customer update(Long id, CustomerRequest request) {
        Customer customer = getById(id);

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
        customer.setPassword(request.password());

        return customerRepository.save(customer);


    }
}
