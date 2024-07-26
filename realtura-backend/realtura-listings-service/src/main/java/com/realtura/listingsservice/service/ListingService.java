package com.realtura.listingsservice.service;

import com.realtura.listingsservice.client.subscriptions.service.SubscriptionService;
import com.realtura.listingsservice.converter.ListingConverter;
import com.realtura.listingsservice.dto.request.ListingDeleteRequest;
import com.realtura.listingsservice.dto.request.ListingSaveRequest;
import com.realtura.listingsservice.dto.request.ListingSearchRequest;
import com.realtura.listingsservice.dto.response.CreateResponse;
import com.realtura.listingsservice.dto.response.GenericResponse;
import com.realtura.listingsservice.dto.response.ListingResponse;
import com.realtura.listingsservice.exception.ExceptionMessages;
import com.realtura.listingsservice.model.Address;
import com.realtura.listingsservice.model.Listing;
import com.realtura.listingsservice.repository.ListingRepository;
import com.realtura.listingsservice.repository.specification.ListingDeleteSpecification;
import com.realtura.listingsservice.repository.specification.ListingSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final SubscriptionService subscriptionService;

    public GenericResponse<?> save(ListingSaveRequest request) {
        Listing listing = ListingConverter.toListing(request, request.getAddress());
        GenericResponse<?> response = subscriptionService.useCredit(request.getUserId());
        if (response.getStatus().equals("FAILED")) return response;
        Listing createdListing= listingRepository.save(listing);
        return GenericResponse.success(new CreateResponse(createdListing.getId()));
    }

    //@Cacheable(value = "products", cacheNames = "products")
    public List<ListingResponse> getAllByFilter(ListingSearchRequest request) {

        Specification<Listing> listingSpecification = ListingSpecification.initListingSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        Page<Listing> listings = listingRepository.findAll(listingSpecification, pageRequest);

        log.info("db'den getirildi. listing size:{}", listings.getSize());

        return ListingConverter.toResponse(listings.stream().toList());
    }


    public GenericResponse<?> update(Long id, ListingSaveRequest request) {
        Optional<Listing> listingOptional = listingRepository.findById(id);

        if (listingOptional.isPresent()) {
            Listing listing = listingOptional.get();

            if(listing.getUserId() != null && !listing.getUserId().equals(request.getUserId())) {
                log.error(ExceptionMessages.LISTING_NOT_FOUND);
                return GenericResponse.failed(ExceptionMessages.LISTING_NOT_FOUND);
            }
            if (request.getDescription() != null) {
                listing.setDescription(request.getDescription());
            }
            if (request.getNetArea() != null) {
                listing.setNetArea(request.getNetArea());
            }
            if (request.getGrossArea() != null) {
                listing.setGrossArea(request.getGrossArea());
            }
            if (request.getBuiltIn() != null) {
                listing.setBuiltIn(request.getBuiltIn());
            }
            if (request.getHeatingType() != null) {
                listing.setHeatingType(request.getHeatingType());
            }
            if (request.getBathroom() != 0) {
                listing.setBathroom(request.getBathroom());
            }
            if (request.getBedroom() != 0) {
                listing.setBedroom(request.getBedroom());
            }
            if (request.getPropertyType() != null) {
                listing.setPropertyType(request.getPropertyType());
            }
            if (request.getListingType() != null) {
                listing.setListingType(request.getListingType());
            }
            if (request.getIsActive() != null) {
                listing.setActive(request.getIsActive());
            }
            if (request.getTitle() != null) {
                listing.setTitle(request.getTitle());
            }
            if (request.getAddress() != null) {
                Address address = listing.getAddress();
                Address newAddress = request.getAddress();

                if (newAddress.getCity() != null) {
                    address.setCity(newAddress.getCity());
                }
                if (newAddress.getCountry() != null) {
                    address.setCountry(newAddress.getCountry());
                }
                if (newAddress.getZipCode() != null) {
                    address.setZipCode(newAddress.getZipCode());
                }
                if (newAddress.getState() != null) {
                    address.setState(newAddress.getState());
                }
                if (newAddress.getStreet() != null) {
                    address.setStreet(newAddress.getStreet());
                }
                if (newAddress.getBlockNumber() != 0) {
                    address.setBlockNumber(newAddress.getBlockNumber());
                }
                if (newAddress.getFloorNumber() != 0) {
                    address.setFloorNumber(newAddress.getFloorNumber());
                }
                if (newAddress.getFlatNumber() != 0) {
                    address.setFlatNumber(newAddress.getFlatNumber());
                }
            }

            listingRepository.save(listing);
            return GenericResponse.success("listing is updated");
        }
        log.error(ExceptionMessages.LISTING_NOT_FOUND);
        return GenericResponse.failed(ExceptionMessages.LISTING_NOT_FOUND);
    }

    @DeleteMapping
    public GenericResponse<?> delete(ListingDeleteRequest request) {
        Specification<Listing> listingDeleteSpecification = ListingDeleteSpecification.initListingSpecification(request);

        List<Listing> listings = listingRepository.findAll(listingDeleteSpecification);

        if (listings.isEmpty()) {
            log.error(ExceptionMessages.LISTING_NOT_FOUND);
            return GenericResponse.failed(ExceptionMessages.LISTING_NOT_FOUND);
        }
        listingRepository.deleteById(request.getId());
        log.info("Listing deleted" + request.getId());
        return GenericResponse.success("listing deleted");
    }
}
