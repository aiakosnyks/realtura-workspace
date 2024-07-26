package com.realtura.listingsservice.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "city")
    private String city;
    @Column (name = "country")
    private String country;
    @Column (name = "zipCode")
    private String zipCode;
    @Column (name = "state")
    private String state;
    @Column (name = "street")
    private String street;
    @Column (name = "blockNumber")
    private int blockNumber;
    @Column (name = "floorNumber")
    private int floorNumber;
    @Column (name = "flatNumber")
    private int flatNumber;
}
