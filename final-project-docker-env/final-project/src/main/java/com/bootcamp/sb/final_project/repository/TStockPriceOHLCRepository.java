package com.bootcamp.sb.final_project.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.sb.final_project.entity.StockPriceOHLCEntity;

public interface TStockPriceOHLCRepository extends JpaRepository<StockPriceOHLCEntity, Long> {
  Optional<StockPriceOHLCEntity> findTopBySymbolOrderByTimestampDesc(String symbol);
  Optional<StockPriceOHLCEntity> findTopBySymbolOrderByTimestamp(String symbol);
  Optional<StockPriceOHLCEntity> findBySymbolAndTimestamp(String symbol, Long timestamp);
  List<StockPriceOHLCEntity> findAllBySymbolAndTimestampGreaterThanEqualOrderByTimestamp(String symbol, Long timestamp);
}
