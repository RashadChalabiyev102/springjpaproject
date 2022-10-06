package com.example.responseentity.service;

import com.example.responseentity.entity.Customer;
import com.example.responseentity.exception.CustomerNotFoundException;
import com.example.responseentity.exception.CustomerNotNullException;
import com.example.responseentity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        if(customer.getName().isEmpty()){
            throw new CustomerNotNullException("Username must be not null");
        }
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customer;
    }

    public void deleteById(Long customerId) {
        boolean exits = customerRepository.existsById(customerId);
        if(!exits) {
            throw new IllegalStateException("Customer ID not found");
        }
        customerRepository.deleteById(customerId);
    }
}
