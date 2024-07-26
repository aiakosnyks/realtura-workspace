package com.realtura.listingsservice;

import com.realtura.listingsservice.controller.ListingController;
import com.realtura.listingsservice.converter.ListingConverter;
import com.realtura.listingsservice.dto.request.ListingSaveRequest;
import com.realtura.listingsservice.dto.response.CreateResponse;
import com.realtura.listingsservice.dto.response.GenericResponse;
import com.realtura.listingsservice.model.Address;
import com.realtura.listingsservice.model.Listing;
import com.realtura.listingsservice.model.enums.HeatingType;
import com.realtura.listingsservice.model.enums.ListingType;
import com.realtura.listingsservice.service.ListingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListingsServiceApplicationTests {

    @Autowired
    private ListingController listingController;

    @Autowired
    private ListingService listingService;

    @Autowired
    private ListingService subscriptionService;
    @MockBean
    private ListingConverter listingConverter;

    @Test
    public void testSaveListingSuccess() {

        ListingSaveRequest request = new ListingSaveRequest();
        request.setAddress(new Address());
        request.setBuiltIn(1);
        request.setBedroom(1);
        request.setBathroom(1);
        request.setNetArea(1);
        request.setGrossArea(1);
        request.setHeatingType(HeatingType.HEAT_PUMPS);
        request.setListingType(ListingType.SALE);
        request.setPhoto("test");
        request.setPrice(1);
        request.setTitle("test");
        request.setDescription("test");
        request.setUserId(1L);
        ResponseEntity<GenericResponse<?>> response = listingController.save(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus().equals("SUCCESS"));
    }
    @Test
    public void testSaveListingFail() {

    }
}
