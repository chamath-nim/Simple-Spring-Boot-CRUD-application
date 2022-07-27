package com.example.mobitelproduct.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String pkgName;         //Package Name

    @Column(nullable = false)
    private String pkgRental;       // Package Rental

    private String pkgTotalData;    // Package Total Data

    private String pkgAnyData;      // Package Anytime Data Bundle

    private String pkgNightData;    // Package Night time Data Bundle

    private String pkg4GData;       //Package Bonus 4G Data Bundle

    @Column(nullable = false)
    private String pkgValidity;     //Package Validity Time

    private String requestedBy;     // The Person Package Requested
}
