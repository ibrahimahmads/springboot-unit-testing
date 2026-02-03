package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Purchase;
import com.enigmacamp.tokonyadia.entity.PurchaseDetail;
import jakarta.transaction.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PurchaseService{
	Purchase transaction(Purchase purchase);
	List<PurchaseDetail> getDetailsTransaction(UUID id);
	List<Purchase> getDetailTransaction();
}


