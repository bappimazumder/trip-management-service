package com.bappi.tripservice.utils.mapper;

import com.bappi.tripservice.model.dto.TripInfoRequestDto;
import com.bappi.tripservice.model.dto.TripInfoResponseDto;
import com.bappi.tripservice.model.entity.TripInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TripInformationMapper {
    TripInformation map(TripInfoRequestDto dto);

    @Mapping(source = "dropOffDistrict.nameEn", target = "dropOffDistrictName")
    @Mapping(source = "dropOffDistrict.id", target = "dropOffDistrictId")
    @Mapping(source = "pickupDistrict.nameEn", target = "pickupDistrictName")
    @Mapping(source = "pickupDistrict.id", target = "pickupDistrictId")
    TripInfoResponseDto map(TripInformation obj);

}
