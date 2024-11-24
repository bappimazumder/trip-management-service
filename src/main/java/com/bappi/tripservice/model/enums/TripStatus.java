package com.bappi.tripservice.model.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public enum TripStatus {

    CREATED("Created"),
    BOOKED("Booked"),
    RUNNING("Running"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled)");

    @Getter
    private final String message;

    TripStatus(String message) {
        this.message = message;
    }

    private static final Map<String, TripStatus> tripStatusMap = new HashMap<>();

    static {
        for (TripStatus tripStatus : TripStatus.values()) {
            if(!tripStatusMap.containsKey(tripStatus.getMessage().toLowerCase())) {
                tripStatusMap.put(tripStatus.getMessage().toLowerCase(), tripStatus);
            }
        }
    }

    public static boolean isValidTripStatus(String value) {
        try {
            TripStatus status = TripStatus.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
