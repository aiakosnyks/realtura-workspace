package com.realtura.listingsservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressSaveRequest {

    private String city;
    private String country;
    private String zipCode;
    private String state;
    private String street;
    private int blockNumber;
    private int floorNumber;
    private int flatNumber;
}
