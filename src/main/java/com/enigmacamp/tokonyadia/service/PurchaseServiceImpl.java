package com.enigmacamp.tokonyadia.service;


import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.entity.Purchase;
import com.enigmacamp.tokonyadia.entity.PurchaseDetail;
import com.enigmacamp.tokonyadia.repository.PurchaseDetailRepository;
import com.enigmacamp.tokonyadia.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{

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
        Purchase purchase1 = purchaseRepository.save(purchase);
        purchase1.setTransactionDate(LocalDate.now());

        List<PurchaseDetail> purchaseDetails = purchase.getPurchaseDetails();

        purchaseDetails.forEach( p -> {
            p.setPurchase(purchase1);
            Product product = productService.getProductById(p.getProduct().getId());
            if (product.getStock() < p.getQuantity()) {
                throw new IllegalStateException(
                        "Stok produk " + product.getProductName() +
                                " tidak mencukupi. Stok: " + product.getStock() +
                                ", Dibeli: " + p.getQuantity()
                );
            }
            product.setStock(product.getStock() - p.getQuantity());
            p.setPriceSell(product.getProductPrice());

            purchaseDetailService.savePurchaseDetail(p);
        });

        return purchase1;
    }
}
