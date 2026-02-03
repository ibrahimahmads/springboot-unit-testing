package com.enigmacamp.tokonyadia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailResponse {
	private String productName;
	private Integer quantity;
	private Double totalPrice;
}
