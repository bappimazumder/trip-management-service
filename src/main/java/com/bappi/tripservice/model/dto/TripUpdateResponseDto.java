package com.bappi.tripservice.model.dto;

import com.bappi.tripservice.config.IAPIErrorCode;
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

    private String responseMessage;

    private IAPIErrorCode errorCode;
}
