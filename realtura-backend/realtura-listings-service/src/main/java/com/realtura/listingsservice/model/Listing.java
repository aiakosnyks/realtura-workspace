package com.realtura.listingsservice.model;

import com.realtura.listingsservice.model.enums.HeatingType;
import com.realtura.listingsservice.model.enums.ListingType;
import com.realtura.listingsservice.model.enums.PropertyType;
import com.realtura.listingsservice.model.enums.StatusType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "title")
    private String title;
    @Column(name = "description" , nullable = true)
    private String description;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @Column(name = "netArea")
    private Integer netArea;
    @Column(name = "grossArea")
    private Integer grossArea;
    @Column(name = "builtIn")
    private Integer builtIn;
    @Enumerated(EnumType.STRING)
    @Column
    private HeatingType heatingType;
    @Column(name = "bathroom")
    private int bathroom;
    @Column(name = "bedroom")
    private int bedroom;
    @Column(name = "isActive")
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    @Column(name = "propertyType")
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    @Column(name = "listingType")
    private ListingType listingType;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status = StatusType.IN_REVIEW;
    @Lob
    @Column(name = "photo", nullable = true)
    private String photo;
    @Column(name = "price")
    private double price;
}


