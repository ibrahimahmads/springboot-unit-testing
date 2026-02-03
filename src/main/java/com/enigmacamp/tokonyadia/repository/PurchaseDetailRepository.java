package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, UUID> {
}
