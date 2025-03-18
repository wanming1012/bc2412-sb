package com.bootcamp.sb.final_project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.sb.final_project.entity.TStockEntity;

public interface TStockRepository extends JpaRepository<TStockEntity, Long> {
  Optional<TStockEntity> findBySymbol(String name);
}
