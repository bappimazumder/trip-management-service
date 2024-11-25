package com.bappi.tripservice.controller;

import com.bappi.tripservice.config.APIErrorCode;
import com.bappi.tripservice.config.ApiPath;
import com.bappi.tripservice.config.CustomException;
import com.bappi.tripservice.config.ServiceExceptionHandler;
import com.bappi.tripservice.model.dto.TripInfoRequestDto;
import com.bappi.tripservice.model.dto.TripInfoResponseDto;
import com.bappi.tripservice.model.dto.TripUpdateRequestDto;
import com.bappi.tripservice.model.dto.TripUpdateResponseDto;
import com.bappi.tripservice.service.TripInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TripInfoResponseDto> create(@RequestBody TripInfoRequestDto requestDto) {
        log.info("Create Trip Info {} ", requestDto.toString());
        ServiceExceptionHandler<TripInfoResponseDto> dataHandler = () -> service.save(requestDto);
        return new ResponseEntity<>(dataHandler.executeHandler(), HttpStatus.CREATED);
    }

    @PutMapping(value = ApiPath.API_UPDATE_TRIP)
    public ResponseEntity<TripUpdateResponseDto> update(@RequestBody TripUpdateRequestDto requestDto) {
        log.info("Update Trip Info {} ", requestDto.toString());
        ServiceExceptionHandler<TripUpdateResponseDto> dataHandler = () -> service.update(requestDto);
        return new ResponseEntity<>(dataHandler.executeHandler(), HttpStatus.OK);
    }

    @GetMapping(value = ApiPath.API_GET_TRIP)
    public ResponseEntity<TripInfoResponseDto> getDetails(@RequestParam(value = "tripCode") String tripCode) {

        if (tripCode.equals("null") || tripCode.isEmpty()) {
            log.error("Get Trip details , trip code is null");
            throw new CustomException(APIErrorCode.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
        }
        TripInfoResponseDto dto = service.getTrip(tripCode);
        log.info("Trip details to return: " + dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
