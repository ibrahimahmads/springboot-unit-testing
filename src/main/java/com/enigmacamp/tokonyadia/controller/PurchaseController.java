package com.enigmacamp.tokonyadia.controller;


import com.enigmacamp.tokonyadia.entity.Purchase;
import com.enigmacamp.tokonyadia.entity.PurchaseDetail;
import com.enigmacamp.tokonyadia.service.PurchaseService;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	PurchaseService purchaseService;
	
	@Autowired
	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
		@PostMapping
		public Purchase createTransaction(@RequestBody Purchase purchase){
			return purchaseService.transaction(purchase);
		}
		
		@GetMapping("/getDetails/{id}")
		List<PurchaseDetail> getDetails(@PathVariable UUID id){
		return purchaseService.getDetailsTransaction(id);
		}
		
		@GetMapping
	public List<Purchase> getDetailTransaction(){
		return purchaseService.getTransaction();
		}
	
	
}
