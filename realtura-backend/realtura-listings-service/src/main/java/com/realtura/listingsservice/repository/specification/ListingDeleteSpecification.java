package com.realtura.listingsservice.repository.specification;

import com.realtura.listingsservice.dto.request.ListingDeleteRequest;
import com.realtura.listingsservice.dto.request.ListingSearchRequest;
import com.realtura.listingsservice.model.Listing;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListingDeleteSpecification {

    public static Specification<Listing> initListingSpecification(ListingDeleteRequest request) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (request.getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), request.getId()));
            }

            if (request.getUserId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("userId"), request.getUserId()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
