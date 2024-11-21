package com.bappi.tripservice.repository;

import com.bappi.tripservice.model.entity.DistrictInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictInfoRepository extends JpaRepository<DistrictInfo, Long> {


}
