package com.bappi.tripservice.service.impl;

import com.bappi.tripservice.config.APIErrorCode;
import com.bappi.tripservice.config.CustomException;
import com.bappi.tripservice.model.dto.TripInfoRequestDto;
import com.bappi.tripservice.model.dto.TripInfoResponseDto;
import com.bappi.tripservice.model.dto.TripUpdateRequestDto;
import com.bappi.tripservice.model.entity.TripInformation;
import com.bappi.tripservice.repository.TripInformationRepository;
import com.bappi.tripservice.service.TripInformationService;
import com.bappi.tripservice.utils.mapper.TripInformationMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TripInformationServiceImpl implements TripInformationService {

    private final TripInformationRepository repository;
    private final TripInformationMapper objectMapper;

    @Autowired
    public TripInformationServiceImpl(TripInformationRepository repository) {
        this.repository = repository;
        this.objectMapper = Mappers.getMapper(TripInformationMapper.class);
    }


    @Override
    public TripInfoResponseDto save(TripInfoRequestDto requestDto) {

        TripInformation tripInformation = objectMapper.map(requestDto);

        return objectMapper.map(repository.save(tripInformation));
    }

    @Override
    public TripInfoResponseDto update(TripUpdateRequestDto requestDto) {

        TripInformation tripInformation = objectMapper.map(requestDto);

        return objectMapper.map(repository.save(tripInformation));
    }

    @Override
    public TripInfoResponseDto getTrip(String code) {
        log.debug("Get Trip Info details by code {} " , code);

        TripInformation tripInfo = repository.findByCode(code);
        if(tripInfo == null){
            throw new CustomException(APIErrorCode.WRONG_INFORMATION_PROVIDED, HttpStatus.BAD_REQUEST);
        }
        return objectMapper.map(tripInfo);
    }
}
