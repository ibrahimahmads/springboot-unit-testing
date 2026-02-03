package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
}
