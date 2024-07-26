package com.realtura.listingsservice.converter;

import com.realtura.listingsservice.dto.request.ListingSaveRequest;
import com.realtura.listingsservice.dto.response.ListingResponse;
import com.realtura.listingsservice.model.Address;
import com.realtura.listingsservice.model.Listing;

import java.util.List;
import java.util.stream.Collectors;

public class ListingConverter {

    public static Listing toListing(ListingSaveRequest request, Address address) {
        return Listing.builder()
                .price(request.getPrice())
                .address(address)
                .photo(request.getPhoto())
                .userId(request.getUserId())
                .description(request.getDescription())
                .title(request.getTitle())
                .netArea(request.getNetArea())
                .grossArea(request.getGrossArea())
                .builtIn(request.getBuiltIn())
                .heatingType(request.getHeatingType())
                .bathroom(request.getBathroom())
                .bedroom(request.getBedroom())
                .propertyType(request.getPropertyType())
                .listingType(request.getListingType())
                .build();
    }

    public static ListingResponse toResponse(Listing listing) {
        return ListingResponse.builder()
                .id(listing.getId())
                .userId(listing.getUserId())
                .address(listing.getAddress())
                .listingType(listing.getListingType())
                .netArea(listing.getNetArea())
                .title(listing.getTitle())
                .builtIn(listing.getBuiltIn())
                .bedroom(listing.getBedroom())
                .bathroom(listing.getBathroom())
                .heatingType(listing.getHeatingType())
                .grossArea(listing.getGrossArea())
                .propertyType(listing.getPropertyType())
                .description(listing.getDescription())
                .price(listing.getPrice())
                .photo(listing.getPhoto())
                .build();
    }

    public static List<ListingResponse> toResponse(List<Listing> listings) {
        return listings
                .stream()
                .map(ListingConverter::toResponse)
                .collect(Collectors.toList());
    }
}
