package com.bootcamp.sb.bc_mtr_station.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;

@Repository
public interface LineRepository extends JpaRepository<LineEntity, Long> {
  Optional<LineEntity> findByName(String name);
}
