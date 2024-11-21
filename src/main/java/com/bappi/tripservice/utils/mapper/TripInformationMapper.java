package com.bappi.tripservice.utils.mapper;

import com.bappi.tripservice.model.dto.TripInfoRequestDto;
import com.bappi.tripservice.model.dto.TripInfoResponseDto;
import com.bappi.tripservice.model.entity.TripInformation;
import org.mapstruct.Mapper;

@Mapper
public interface TripInformationMapper {

    TripInformation map(TripInfoRequestDto dto);

    TripInfoResponseDto map(TripInformation obj);

}
