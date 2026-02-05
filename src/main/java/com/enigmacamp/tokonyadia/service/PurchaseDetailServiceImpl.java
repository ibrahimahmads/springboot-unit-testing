package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.PurchaseDetail;
import com.enigmacamp.tokonyadia.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService{

    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    public PurchaseDetailServiceImpl(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
