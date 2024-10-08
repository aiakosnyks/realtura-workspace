package com.realtura.listingsservice.dto.request;

import com.realtura.listingsservice.model.Address;
import com.realtura.listingsservice.model.enums.HeatingType;
import com.realtura.listingsservice.model.enums.ListingType;
import com.realtura.listingsservice.model.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListingSaveRequest
{
    private double price;
    private String photo;
    private Long userId;
    private String title;
    private String description;
    private Address address;
    private Integer netArea;
    private Integer grossArea;
    private Integer builtIn;
    private HeatingType heatingType;
    private int bathroom;
    private int bedroom;
    private PropertyType propertyType;
    private ListingType listingType;
    private Boolean isActive;
}
