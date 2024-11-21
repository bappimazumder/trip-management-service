package com.bappi.tripservice.repository;

import com.bappi.tripservice.model.entity.TripInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripInformationRepository extends JpaRepository<TripInformation, Long> {

    TripInformation findByCode(String code);
}
