package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findCustomerByFullNameIsLikeIgnoreCaseOrEmailIsLike() {
        Customer customer = Customer.builder()
                .fullName("Ibrahim Saputra")
                .email("ibrahim@email.com")
                .build();

        Customer customer2 = Customer.builder()
                .fullName("Naufal Julian")
                .email("naufal@email.com")
                .build();

        customerRepository.saveAll(List.of(customer,customer2));

        List<Customer> result = customerRepository.findCustomerByFullNameIsLikeIgnoreCaseOrEmailIsLike("%Ibrahim%", "%naufal@email.com");
        assertEquals(2, result.size());
        assertThat(result).hasSize(2);
    }
}