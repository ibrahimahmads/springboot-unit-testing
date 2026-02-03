package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.dto.response.PurchaseResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "t_purchase")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Purchase {
	
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	private LocalDate transactionDate;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(mappedBy = "purchase")
	@JsonIgnoreProperties("purchase")
	private List<PurchaseDetail> purchaseDetails = new ArrayList<>();
	
	
//	public PurchaseResponse toResponse(){
//		return PurchaseResponse.builder()
//				.
//				.build();
//	}
}


