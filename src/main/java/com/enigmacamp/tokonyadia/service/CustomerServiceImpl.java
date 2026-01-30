package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id).get();
    }

    public List<Customer> searchCustomer(String fullName, String email){
        return customerRepository.findCustomerByFullNameIsLikeIgnoreCaseOrEmailIsLike(fullName, email);
    }
}
