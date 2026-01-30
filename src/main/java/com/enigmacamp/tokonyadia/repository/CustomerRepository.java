package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findCustomerByFullNameIsLikeIgnoreCaseOrEmailIsLike(String fullName, String email);
}
