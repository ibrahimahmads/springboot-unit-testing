package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Purchase;

public interface PurchaseService {
    Purchase transaction(Purchase purchase);
}
