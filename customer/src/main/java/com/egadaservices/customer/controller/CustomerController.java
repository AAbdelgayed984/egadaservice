package com.egadaservices.customer.controller;

import com.egadaservices.customer.model.Customer;
import com.egadaservices.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/createCustomer")
    public Customer registerCustomer(@RequestBody Customer customer) {
        log.info("New Customer Registration {}", customer);
        return customerService.createCustomer(customer);
    }

    @GetMapping("getCustomers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

}
