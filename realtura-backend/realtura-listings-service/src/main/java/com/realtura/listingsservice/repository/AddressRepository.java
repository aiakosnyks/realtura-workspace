package com.realtura.listingsservice.repository;

import com.realtura.listingsservice.model.Address;
import com.realtura.listingsservice.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
//    List<Address> findByZipCode(String zipCode);
//    List<Address> findByCity(String city);
//    List<Address> findByCountry(String country);
//    List<Address> findByState(String State);
//    List<Address> findByStreet(String street);
//    Optional<Address> findById(Long id);
//    List<Address> findAllByUserId(Long userId);
//    List<Address> findByFloorNumber(String findByFloorNumber);
//    List<Address> findByBlockNumber(String findByBlockNumber);
//    List<Address> findByFlatNumber(String findByFlatNumber);
}
