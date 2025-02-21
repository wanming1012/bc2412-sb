package com.bootcamp.sb.bc_mtr_station.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;
import com.bootcamp.sb.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;

@Repository
public interface LineStationRepository
    extends JpaRepository<LineStationEntity, Long> {
  List<LineStationEntity> findByLine(LineEntity line);

  Optional<LineStationEntity> findByLineAndCurrentStation(LineEntity line,
      StationEntity currentStation);

  List<LineStationEntity> findByCurrentStation(StationEntity currentStation);
}
