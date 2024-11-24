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
public class TripUpdateRequestDto {

    private String tripCode;

    private String currentStatus;

    private Long transportId;


}
