package com.bappi.tripservice.controller;

import com.bappi.tripservice.config.ApiPath;
import com.bappi.tripservice.model.dto.TripInfoRequestDto;
import com.bappi.tripservice.model.dto.TripInfoResponseDto;
import com.bappi.tripservice.service.TripInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bappi.tripservice.config.ApiPath.API_TRIP_INFO;

@Slf4j
@RestController
@RequestMapping(API_TRIP_INFO)
public class TripInformationController {

    private final TripInformationService service;

    @Autowired
    public TripInformationController(TripInformationService service) {
        this.service = service;
    }

    @PostMapping(value = ApiPath.API_CREATE_TRIP_INFO)
    public ResponseEntity<TripInfoResponseDto> create(@RequestBody TripInfoRequestDto tripRequestDto){
        log.info("Create Trip Info {} ", tripRequestDto.toString());
        TripInfoResponseDto response = service.save(tripRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
