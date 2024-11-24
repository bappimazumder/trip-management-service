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
public class TripInfoResponseDto {

    private String code;

    private Integer pickupDistrictId;

    private String pickupDistrictName;

    private String pickUpAddress;

    private Integer dropOffDistrictId;

    private String dropOffDistrictName;

    private String dropOffAddress;

    private String currentStatus;

    private Timestamp startDate;

    private Timestamp endDate;

    private String realTimeLocation;

    private Long assignedTransport;


}
