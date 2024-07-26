package com.realtura.listingsservice.dto.response;

import com.realtura.listingsservice.model.Address;
import com.realtura.listingsservice.model.enums.HeatingType;
import com.realtura.listingsservice.model.enums.ListingType;
import com.realtura.listingsservice.model.enums.PropertyType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListingResponse {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private Address address;
    private Long addressId;
    private Integer netArea;
    private Integer grossArea;
    private Integer builtIn;
    private HeatingType heatingType;
    private int bathroom;
    private int bedroom;
    private PropertyType propertyType;
    private ListingType listingType;
    private String photo;
    private double price;
}

