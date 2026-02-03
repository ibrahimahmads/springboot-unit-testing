package com.enigmacamp.tokonyadia.dto.response;

import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.entity.PurchaseDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PurchaseResponse {

	private LocalDate transactionDate;
	private Customer customer;
	private List<PurchaseDetail> purchaseDetails;
}
