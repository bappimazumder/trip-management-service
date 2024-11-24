package com.bappi.tripservice.service;

import com.bappi.tripservice.model.dto.TripInfoRequestDto;
import com.bappi.tripservice.model.dto.TripInfoResponseDto;
import com.bappi.tripservice.model.dto.TripUpdateRequestDto;
import com.bappi.tripservice.model.dto.TripUpdateResponseDto;

public interface TripInformationService {

    public TripInfoResponseDto save(TripInfoRequestDto requestDto);

    public TripUpdateResponseDto update(TripUpdateRequestDto requestDto);

    public TripInfoResponseDto getTrip(String code);

}
