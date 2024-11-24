package com.bappi.tripservice.service.impl;

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
import com.bappi.tripservice.service.TripInformationService;
import com.bappi.tripservice.utils.mapper.TripInformationMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class TripInformationServiceImpl implements TripInformationService {
    public static final String TRIP_CODE_PREFIX = "TRIP-";

    private final TripInformationRepository repository;
    private final TripInformationMapper objectMapper;
    private final DistrictInfoRepository districtInfoRepository;

    @Autowired
    public TripInformationServiceImpl(TripInformationRepository repository, DistrictInfoRepository districtInfoRepository) {
        this.repository = repository;
        this.districtInfoRepository = districtInfoRepository;
        this.objectMapper = Mappers.getMapper(TripInformationMapper.class);
    }


    @Override
    public TripInfoResponseDto save(TripInfoRequestDto requestDto) {
        TripInfoResponseDto responseDto = new TripInfoResponseDto();

        Optional<DistrictInfo> pickUpDistrict = districtInfoRepository.findById(requestDto.getPickupDistrictId());
        Optional<DistrictInfo> dropOffDistrict = districtInfoRepository.findById(requestDto.getDropOffDistrictId());

        if (pickUpDistrict.isEmpty()){
            log.error("Invalid pickup district Found");
            responseDto.setResponseMessage("Invalid Pickup District");
            responseDto.setErrorCode(APIErrorCode.INVALID_REQUEST);
            return responseDto;
        }

        if (dropOffDistrict.isEmpty()){
            log.error("Invalid drop off district Found");
            responseDto.setResponseMessage("Invalid Drop-off District");
            responseDto.setErrorCode(APIErrorCode.INVALID_REQUEST);
            return responseDto;
        }

        if(requestDto.getPickUpAddress() == null || requestDto.getPickUpAddress().isEmpty()){
            responseDto.setResponseMessage("Invalid Pickup Address");
            responseDto.setErrorCode(APIErrorCode.INVALID_REQUEST);
            return responseDto;
        }

        if(requestDto.getDropOffAddress() == null || requestDto.getDropOffAddress().isEmpty()){
            responseDto.setResponseMessage("Invalid Drop off Address");
            responseDto.setErrorCode(APIErrorCode.INVALID_REQUEST);
            return responseDto;
        }

        int uniqueId = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        String tripCode = TRIP_CODE_PREFIX + uniqueId;

        TripInformation tripInformation = objectMapper.map(requestDto);
        tripInformation.setCode(tripCode);
        tripInformation.setCurrentStatus(TripStatus.CREATED);
        tripInformation.setCreateDate(new Timestamp(System.currentTimeMillis()));
        tripInformation.setCreateBy(1L);
        tripInformation.setPickupDistrict(pickUpDistrict.get());
        tripInformation.setDropOffDistrict(dropOffDistrict.get());
        responseDto = objectMapper.map(repository.save(tripInformation));
        responseDto.setResponseMessage("Successfully Created");
        return responseDto;
    }

    @Override
    public TripUpdateResponseDto update(TripUpdateRequestDto requestDto) {
        String tripCode = requestDto.getTripCode();
        log.debug("Update Trip Info details by code {} " , tripCode);

        TripInformation tripInformation = repository.findByCode(tripCode);
        if(tripInformation == null) {
            log.error("No Trip Information Found");
            throw new CustomException(APIErrorCode.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
        }

        if(requestDto.getCurrentStatus() != null){
            if(TripStatus.isValidTripStatus(requestDto.getCurrentStatus())){
                tripInformation.setCurrentStatus(TripStatus.valueOf(requestDto.getCurrentStatus()));
            }else{
                log.error("Invalid Trip Status {} ",requestDto.getCurrentStatus());
                TripUpdateResponseDto responseDto = new TripUpdateResponseDto();
                responseDto.setResponseMessage("Invalid Trip Status");
                responseDto.setErrorCode(APIErrorCode.INVALID_REQUEST);
                return responseDto;
            }
        }

        if(requestDto.getTransportId() != null){
            tripInformation.setAssignedTransport(requestDto.getTransportId());
        }

        tripInformation.setUpdateBy(1L);
        tripInformation.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        TripInformation savedObj = repository.save(tripInformation);

        return TripUpdateResponseDto.builder()
                                            .tripCode(savedObj.getCode())
                                            .tripStatus(TripStatus.valueOf(savedObj.getCurrentStatus().name()).getMessage())
                                            .assignedTransportId(savedObj.getAssignedTransport())
                                            .responseMessage("Update Successfully").build();
    }

    @Override
    public TripInfoResponseDto getTrip(String code) {
        log.debug("Get Trip Info details by code {} " , code);

        TripInformation tripInfo = repository.findByCode(code);
        if(tripInfo == null){
            log.error("Trip code is invalid");
            throw new CustomException(APIErrorCode.WRONG_INFORMATION_PROVIDED, HttpStatus.BAD_REQUEST);
        }
        return objectMapper.map(tripInfo);
    }
}
