package com.bappi.tripservice.service;

import com.bappi.tripservice.config.APIErrorCode;
import com.bappi.tripservice.config.CustomException;
import com.bappi.tripservice.model.dto.TripInfoRequestDto;
import com.bappi.tripservice.model.dto.TripInfoResponseDto;
import com.bappi.tripservice.model.dto.TripUpdateRequestDto;
import com.bappi.tripservice.model.dto.TripUpdateResponseDto;
import com.bappi.tripservice.model.entity.DistrictInfo;
import com.bappi.tripservice.model.entity.TripInformation;
import com.bappi.tripservice.model.enums.TripStatus;
import com.bappi.tripservice.repository.DistrictInfoRepository;
import com.bappi.tripservice.repository.TripInformationRepository;
import com.bappi.tripservice.service.impl.TripInformationServiceImpl;
import com.bappi.tripservice.utils.mapper.TripInformationMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TripInformationServiceImplTest {

    @Mock
    private DistrictInfoRepository districtInfoRepository;

    @Mock
    private TripInformationRepository repository;

    @Mock
    private TripInformationMapper tripInformationMapper;

    @InjectMocks
    private TripInformationServiceImpl tripInformationService;

    @Test
    void save_ValidRequest_ReturnsSuccess() {

        TripInfoRequestDto requestDto = new TripInfoRequestDto();
        requestDto.setPickupDistrictId(1);
        requestDto.setDropOffDistrictId(2);
        requestDto.setPickUpAddress("Pickup Address");
        requestDto.setDropOffAddress("Drop-off Address");

        DistrictInfo pickupDistrict = new DistrictInfo();
        pickupDistrict.setId(1);

        DistrictInfo dropOffDistrict = new DistrictInfo();
        dropOffDistrict.setId(2);

        TripInformation tripInformation = new TripInformation();
        TripInfoResponseDto expectedResponse = new TripInfoResponseDto();
        expectedResponse.setResponseMessage("Successfully Created");

        when(districtInfoRepository.findById(1)).thenReturn(Optional.of(pickupDistrict));
        when(districtInfoRepository.findById(2)).thenReturn(Optional.of(dropOffDistrict));
        when(tripInformationMapper.map(any(TripInfoRequestDto.class))).thenReturn(tripInformation);
        when(repository.save(any(TripInformation.class))).thenReturn(tripInformation);
        when(tripInformationMapper.map(any(TripInformation.class))).thenReturn(expectedResponse);

        TripInfoResponseDto response = tripInformationService.save(requestDto);

        assertEquals("Successfully Created", response.getResponseMessage());
        verify(districtInfoRepository).findById(1);
        verify(districtInfoRepository).findById(2);
        verify(repository).save(any(TripInformation.class));
    }


    @Test
    void save_InvalidPickupDistrict_ReturnsError() {

        TripInfoRequestDto requestDto = new TripInfoRequestDto();
        requestDto.setPickupDistrictId(1);
        requestDto.setDropOffDistrictId(2);
        requestDto.setPickUpAddress("Pickup Address");
        requestDto.setDropOffAddress("Drop-off Address");

        when(districtInfoRepository.findById(1)).thenReturn(Optional.empty());
        when(districtInfoRepository.findById(2)).thenReturn(Optional.of(new DistrictInfo()));

        TripInfoResponseDto expectedResponse = new TripInfoResponseDto();
        expectedResponse.setResponseMessage("Invalid Pickup District");
        expectedResponse.setErrorCode(APIErrorCode.INVALID_REQUEST);

        TripInfoResponseDto response = tripInformationService.save(requestDto);

        assertEquals("Invalid Pickup District", response.getResponseMessage());
        assertEquals(APIErrorCode.INVALID_REQUEST, response.getErrorCode());
    }

    @Test
    void save_InvalidPickupAddress_ReturnsError() {

        TripInfoRequestDto requestDto = new TripInfoRequestDto();
        requestDto.setPickupDistrictId(1);
        requestDto.setDropOffDistrictId(2);
        requestDto.setPickUpAddress("");  // Invalid pickup address
        requestDto.setDropOffAddress("Drop-off Address");

        DistrictInfo pickupDistrict = new DistrictInfo();
        pickupDistrict.setId(1);

        DistrictInfo dropOffDistrict = new DistrictInfo();
        dropOffDistrict.setId(2);

        when(districtInfoRepository.findById(1)).thenReturn(Optional.of(pickupDistrict));
        when(districtInfoRepository.findById(2)).thenReturn(Optional.of(dropOffDistrict));

        TripInfoResponseDto expectedResponse = new TripInfoResponseDto();
        expectedResponse.setResponseMessage("Invalid Pickup Address");
        expectedResponse.setErrorCode(APIErrorCode.INVALID_REQUEST);

        TripInfoResponseDto response = tripInformationService.save(requestDto);

        assertEquals("Invalid Pickup Address", response.getResponseMessage());
        assertEquals(APIErrorCode.INVALID_REQUEST, response.getErrorCode());
    }

    @Test
    void update_ValidRequest_ReturnsSuccess() {

        TripUpdateRequestDto requestDto = new TripUpdateRequestDto();
        requestDto.setTripCode("TRIP123");
        requestDto.setCurrentStatus("COMPLETED");
        requestDto.setTransportId(5L);

        TripInformation existingTrip = new TripInformation();
        existingTrip.setCode("TRIP123");
        existingTrip.setCurrentStatus(TripStatus.BOOKED);
        existingTrip.setAssignedTransport(1L);

        TripInformation updatedTrip = new TripInformation();
        updatedTrip.setCode("TRIP123");
        updatedTrip.setCurrentStatus(TripStatus.COMPLETED);
        updatedTrip.setAssignedTransport(5L);

        when(repository.findByCode("TRIP123")).thenReturn(existingTrip);
        when(repository.save(any(TripInformation.class))).thenReturn(updatedTrip);

        TripUpdateResponseDto response = tripInformationService.update(requestDto);

        assertEquals("Update Successfully", response.getResponseMessage());
        assertEquals("COMPLETED", response.getTripStatus());
        assertEquals(5L, response.getAssignedTransportId());
    }

    @Test
    void update_TripNotFound_ReturnsError() {
        TripUpdateRequestDto requestDto = new TripUpdateRequestDto();
        requestDto.setTripCode("INVALID_CODE");

        when(repository.findByCode("INVALID_CODE")).thenReturn(null);

        CustomException exception = assertThrows(CustomException.class, () -> {
            tripInformationService.update(requestDto);
        });

        assertEquals(APIErrorCode.INVALID_REQUEST, exception.getCode());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void update_InvalidTripStatus_ReturnsError() {

        TripUpdateRequestDto requestDto = new TripUpdateRequestDto();
        requestDto.setTripCode("TRIP123");
        requestDto.setCurrentStatus("INVALID_STATUS");

        TripInformation existingTrip = new TripInformation();
        existingTrip.setCode("TRIP123");
        existingTrip.setCurrentStatus(TripStatus.RUNNING);

        when(repository.findByCode("TRIP123")).thenReturn(existingTrip);

        TripUpdateResponseDto response = tripInformationService.update(requestDto);

        assertEquals("Invalid Trip Status", response.getResponseMessage());
        assertEquals(APIErrorCode.INVALID_REQUEST, response.getErrorCode());
    }

    @Test
    void update_ValidTransportId_ReturnsUpdatedInfo() {
        TripUpdateRequestDto requestDto = new TripUpdateRequestDto();
        requestDto.setTripCode("TRIP123");
        requestDto.setTransportId(10L);  // New transport ID

        TripInformation existingTrip = new TripInformation();
        existingTrip.setCode("TRIP123");
        existingTrip.setCurrentStatus(TripStatus.RUNNING);

        TripInformation updatedTrip = new TripInformation();
        updatedTrip.setCode("TRIP123");
        updatedTrip.setCurrentStatus(TripStatus.RUNNING);
        updatedTrip.setAssignedTransport(10L);  // Updated transport ID

        when(repository.findByCode("TRIP123")).thenReturn(existingTrip);
        when(repository.save(any(TripInformation.class))).thenReturn(updatedTrip);

        TripUpdateResponseDto response = tripInformationService.update(requestDto);

        assertEquals("Update Successfully", response.getResponseMessage());
        assertEquals(10L, response.getAssignedTransportId());
    }


    @Test
    void getTrip_ValidCode_ReturnsTripInfo() {
        String tripCode = "TRIP123";
        TripInformation tripInformation = new TripInformation();
        tripInformation.setCode(tripCode);
        tripInformation.setCurrentStatus(TripStatus.BOOKED);
        tripInformation.setAssignedTransport(1L);

        TripInfoResponseDto responseDto = new TripInfoResponseDto();
        responseDto.setCode(tripCode);
        responseDto.setCurrentStatus(TripStatus.BOOKED.name());
        responseDto.setAssignedTransport(1L);

        when(repository.findByCode(tripCode)).thenReturn(tripInformation);
        when(tripInformationMapper.map(tripInformation)).thenReturn(responseDto);

        TripInfoResponseDto response = tripInformationService.getTrip(tripCode);

        assertNotNull(response);
        assertEquals(tripCode, response.getCode());
        assertEquals(TripStatus.BOOKED.name(), response.getCurrentStatus());
        assertEquals(1L, response.getAssignedTransport());
    }
}
