package com.realtura.realturamain.model;

import com.realtura.realturamain.model.enums.HeatingType;
import com.realtura.realturamain.model.enums.ListingType;
import com.realtura.realturamain.model.enums.PropertyType;
import com.realtura.realturamain.model.enums.StatusType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
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
    @OneToOne
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

    public void Listing(Long userId, String title, String description, ListingType listingType, PropertyType propertyType) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.listingType = listingType;
        this.propertyType = propertyType;
    }
}


