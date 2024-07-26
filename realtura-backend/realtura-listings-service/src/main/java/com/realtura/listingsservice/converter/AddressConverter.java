package com.realtura.listingsservice.converter;

import com.realtura.listingsservice.dto.request.AddressSaveRequest;
import com.realtura.listingsservice.model.Address;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressConverter {

        public static Address toAddress(AddressSaveRequest request) {
            return Address.builder()
                    .city(request.getCity())
                    .state(request.getState())
                    .country(request.getCountry())
                    .street(request.getStreet())
                    .zipCode(request.getZipCode())
                    .blockNumber(request.getBlockNumber())
                    .flatNumber(request.getFlatNumber())
                    .floorNumber(request.getFloorNumber())
                    .build();
        }

//        public static AddressResponse toResponse(Address address) {
//            return com.realtura.realturamain.client.user.dto.response.UserResponse.builder()
//                    .nameSurname(user.getNameSurname())
//                    .username(user.getUsername())
//                    .email(user.getEmail())
//                    .phoneNumber(user.getPhoneNumber())
//                    .isActive(user.getIsActive())
//                    .country(user.getCountry())
//                    .build();
//        }
//
//        public static Set<com.realtura.realturamain.client.user.dto.response.UserResponse> toResponse(List<User> users) {
//            return users
//                    .stream()
//                    .map(UserConverter::toResponse)
//                    .collect(Collectors.toSet());
//        }
//    }
}
