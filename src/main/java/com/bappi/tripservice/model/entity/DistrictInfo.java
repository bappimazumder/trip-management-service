package com.bappi.tripservice.model.entity;

import com.bappi.tripservice.config.DistrictDBConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = DistrictDBConstant.DISTRICT_INFO_TABLE)
public class DistrictInfo {

    @Id
    @Column(name = DistrictDBConstant.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DistrictDBConstant.CODE, unique = true)
    private String code;

    @Column(name = DistrictDBConstant.NAME_EN)
    private String nameEn;

    @Column(name = DistrictDBConstant.NAME_BN)
    private String nameBn;

    @Column(name = DistrictDBConstant.ADDRESS)
    private String address;

    @Column(name = DistrictDBConstant.ACTIVE_STATUS)
    private Boolean activeStatus;

    @Column(name = DistrictDBConstant.CREATED_DATE)
    private Date createdDate;

    @Column(name = DistrictDBConstant.CREATED_BY)
    private Long createdBy;

    @Column(name = DistrictDBConstant.UPDATED_BY)
    private Long updatedBy;

    @Column(name = DistrictDBConstant.UPDATED_DATE)
    private Date updatedDate;

}
