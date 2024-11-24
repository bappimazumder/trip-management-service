package com.bappi.tripservice.model.entity;


import com.bappi.tripservice.model.enums.TripStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import static com.bappi.tripservice.config.TripInfoDBConstant.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = TRIP_INFORMATION)
public class TripInformation {

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = CODE,unique=true)
    private String code;

    @ManyToOne
    @JoinColumn(name = PICKUP_DISTRICT)
    private DistrictInfo pickupDistrict;

    @Column(name = PICKUP_ADDRESS)
    private String pickUpAddress;

    @ManyToOne
    @JoinColumn(name = DROP_OFF_DISTRICT)
    private DistrictInfo dropOffDistrict;

    @Column(name = DROP_OFF_ADDRESS)
    private String dropOffAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = CURRENT_STATUS)
    private TripStatus currentStatus;

    @Column(name = ASSIGNED_TRANSPORT)
    private Long assignedTransport;

    @Column(name = REAL_TIME_LOCATION)
    private String realTimeLocation;

    @Column(name = START_DATE)
    private Timestamp startDate;

    @Column(name = END_DATE)
    private Timestamp endDate;

    @Column(name = CREATE_DATE)
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());

    @Column(name = CREATED_BY)
    private Long createBy;

    @Column(name = UPDATE_DATE)
    private Timestamp updateDate = new Timestamp(System.currentTimeMillis());

    @Column(name = UPDATED_BY)
    private Long updateBy;

}
