package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.utils.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "m_customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "fullname", length = 100, nullable = false)
    private String fullName;
    private String email;
    private String address;
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
