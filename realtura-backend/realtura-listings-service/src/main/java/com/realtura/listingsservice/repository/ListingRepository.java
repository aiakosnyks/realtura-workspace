package com.realtura.listingsservice.repository;

import com.realtura.listingsservice.model.Address;
import com.realtura.listingsservice.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {
    List<Listing> findByUserId(Long userId);
    List<Listing> findByIsActive(boolean isActive);
}
