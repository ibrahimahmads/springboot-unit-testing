package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer getCustomerById(UUID id);
    List<Customer> searchCustomer(String fullName, String email);
}
