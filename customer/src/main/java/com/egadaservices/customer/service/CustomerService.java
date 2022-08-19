package com.egadaservices.customer.service;

import com.egadaservices.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer createCustomer(Customer customer);
    public List<Customer> getCustomers();
}
