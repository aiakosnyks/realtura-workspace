package com.realtura.listingsservice.repository.specification;

import com.realtura.listingsservice.dto.request.ListingSearchRequest;
import com.realtura.listingsservice.model.Listing;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListingFilterSpecification {

    public static Specification<Listing> initListingSpecification(ListingSearchRequest request) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("isActive"), request.getIsActive()));
            addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("userId"), request.getUserId()));
            addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("listingType"), request.getListingType()));
            addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("propertyType"), request.getPropertyType()));
            addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("heatingType"), request.getHeatingType()));

            if (request.getAddress() != null) {
                addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("address").get("city"), request.getAddress().getCity()));
                addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("address").get("state"), request.getAddress().getState()));
                addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("address").get("country"), request.getAddress().getCountry()));
                addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("address").get("zipCode"), request.getAddress().getZipCode()));
                addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("address").get("street"), request.getAddress().getStreet()));
            }

            addPredicateIfNotZero(predicateList, criteriaBuilder.equal(root.get("bedroom"), request.getBedroom()));
            addPredicateIfNotZero(predicateList, criteriaBuilder.equal(root.get("bathrooms"), request.getBathroom()));

            addRangePredicate(predicateList, criteriaBuilder, root.get("builtIn"), request.getBuiltInLowerBound(), request.getBuiltInUpperBound());
            addRangePredicate(predicateList, criteriaBuilder, root.get("price"), request.getPriceLowerBound(), request.getPriceUpperBound());
            addRangePredicate(predicateList, criteriaBuilder, root.get("netArea"), request.getNetAreaLowerBound(), request.getNetAreaUpperBound());
            addRangePredicate(predicateList, criteriaBuilder, root.get("grossArea"), request.getGrossAreaLowerBound(), request.getGrossAreaUpperBound());

            addPredicateIfNotNull(predicateList, criteriaBuilder.equal(root.get("id"), request.getId()));

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

    private static void addPredicateIfNotNull(List<Predicate> predicateList, Predicate predicate) {
        if (predicate != null) {
            predicateList.add(predicate);
        }
    }

    private static void addPredicateIfNotZero(List<Predicate> predicateList, Predicate predicate) {
        if (predicate.getExpressions().get(1) != null && !predicate.getExpressions().get(1).equals(0)) {
            predicateList.add(predicate);
        }
    }

    private static void addRangePredicate(List<Predicate> predicateList, CriteriaBuilder criteriaBuilder, Path<Comparable> path, Comparable lowerBound, Comparable upperBound) {
        if (lowerBound != null && upperBound != null) {
            predicateList.add(criteriaBuilder.between(path, lowerBound, upperBound));
        } else if (lowerBound != null) {
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(path, lowerBound));
        } else if (upperBound != null) {
            predicateList.add(criteriaBuilder.lessThanOrEqualTo(path, upperBound));
        }
    }
}
