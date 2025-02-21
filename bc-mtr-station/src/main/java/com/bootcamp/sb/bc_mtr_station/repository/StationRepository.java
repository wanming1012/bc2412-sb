package com.bootcamp.sb.bc_mtr_station.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {
  Optional<StationEntity> findByName(String name);
}
