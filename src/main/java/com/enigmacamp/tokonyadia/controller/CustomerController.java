package com.enigmacamp.tokonyadia.controller;


import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.service.CustomerService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustById(@PathVariable UUID id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/search")
    List<Customer> searchCustomer(@RequestParam(name = "fullName", required = false) String fullName,
                                  @RequestParam(name = "email", required = false) String email){
        return customerService.searchCustomer(fullName, email);
    }


}
