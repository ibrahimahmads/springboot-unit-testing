package com.enigmacamp.tokonyadia.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "t_purchase_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseDetail {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private Integer quantity;
    private Double priceSell;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private  Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnoreProperties("purchaseDetails")
    private Purchase purchase;

}
