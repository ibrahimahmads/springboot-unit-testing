package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "m_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity{

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String productName;
    private Double productPrice;
    private Integer stock;

    public ProductResponse toResponse(){
        return ProductResponse.builder()
                .id(getId())
                .name(getProductName())
                .price(getProductPrice())
                .stock(getStock())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdateAt())
                .build();
    }
}
