package com.example.responseentity.controller;

import com.example.responseentity.entity.Customer;
import com.example.responseentity.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> allCustomers() {
        List<Customer> getCustomers = customerService.allCustomers();
        return new ResponseEntity<>(getCustomers, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer addCustomer = customerService.save(customer);
        return new ResponseEntity<>(addCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        Customer customerById = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long customerId) {
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
