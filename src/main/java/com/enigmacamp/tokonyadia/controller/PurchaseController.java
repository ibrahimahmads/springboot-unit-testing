package com.enigmacamp.tokonyadia.controller;


import com.enigmacamp.tokonyadia.entity.Purchase;
import com.enigmacamp.tokonyadia.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
