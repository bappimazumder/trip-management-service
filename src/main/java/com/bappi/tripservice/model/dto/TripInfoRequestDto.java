package com.bappi.tripservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TripInfoRequestDto {

    private Integer pickupDistrictId;

    private String pickUpAddress;

    private Integer dropOffDistrictId;

    private String dropOffAddress;

    private Timestamp startDate;

    private Timestamp endDate;

}
