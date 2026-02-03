package com.enigmacamp.tokonyadia.service;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.entity.Purchase;
import com.enigmacamp.tokonyadia.entity.PurchaseDetail;
import com.enigmacamp.tokonyadia.repository.PurchaseRepository;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	PurchaseRepository purchaseRepository;
	PurchaseDetailService purchaseDetailService;
	ProductService productService;
	
	@Autowired
	public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseDetailService purchaseDetailService, ProductService productService) {
		this.purchaseRepository = purchaseRepository;
		this.purchaseDetailService = purchaseDetailService;
		this.productService = productService;
	}
	
	@Override
	@Transactional
	public Purchase transaction(Purchase purchase) {
		purchase.setTransactionDate(LocalDate.now());
		
		Purchase savedPurchase = purchaseRepository.save(purchase);
		List<PurchaseDetail> purchaseDetails = purchase.getPurchaseDetails();
		purchaseDetails.forEach(pd -> {
			Product product = productService.getProductById(pd.getProduct().getId());
			
			pd.setPriceSell(product.getProductPrice() * pd.getQuantity());
//			pd.s
			
			pd.setPurchase(savedPurchase);
			
			purchaseDetailService.savePurchaseDetail(pd);
		});
		
		return savedPurchase;
	}

//	@Override
//	public ResponseEntity<List<Purchase>> getAll(){
//		List<Purchase> purchases = purchaseRepository.findAll();
//		purchases.stream()
//				.(purchases :: toResponse)
//				.map()
//	}
	
	public List<PurchaseDetail> getDetailsTransaction(UUID id) {
		Purchase purchase = purchaseRepository.findById(id).orElse(null);
		if (purchase == null) return List.of();
		
		for (PurchaseDetail pd : purchase.getPurchaseDetails()) {
			double total = pd.getProduct().getProductPrice() * pd.getQuantity();
			pd.setPriceSell(total);
		}
		
		return purchase.getPurchaseDetails();
	}

//	@Override
//	public ResponseEntity<List<Purchase>> getAll(){
//	return purchaseRepository.findAll().stream()
//			.map(purch
//	}
	
	@Override
	public List<Purchase> getDetailTransaction() {
		return purchaseRepository.findAll();
//	}
	}
}


