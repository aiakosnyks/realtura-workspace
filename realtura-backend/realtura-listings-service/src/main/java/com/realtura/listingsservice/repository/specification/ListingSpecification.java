package com.realtura.listingsservice.repository.specification;

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
public class ListingSpecification {

    public static Specification<Listing> initListingSpecification(ListingSearchRequest request) {
        return (root, query, criteriaBuilder) -> {

//            Join<Listing, User> customerJoin = root.join("customer");

            List<Predicate> predicateList = new ArrayList<>();

            if (request.getIsActive() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("isActive"), request.getIsActive())); //like ile aramaya çevirin
            }

            if (request.getUserId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("userId"), request.getUserId()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
