package com.bappi.tripservice.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TripUpdateResponseDto {

    String tripCode;
    String tripStatus;
    Long assignedTransportId;
    String message;
    String errorCode;
}
