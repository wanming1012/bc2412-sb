package com.bootcamp.sb.final_project.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.sb.final_project.entity.StockPriceEntity;

public interface TStockPriceRepository extends JpaRepository<StockPriceEntity, Long> {
  Optional<StockPriceEntity> findTopBySymbolAndTypeAndMarketTimeGreaterThanEqualOrderByRegularMarketTimeDesc(String symbol, String type, LocalDateTime systemTime);
  Optional<StockPriceEntity> findTopBySymbolOrderByRegularMarketTimeDesc(String symbol);
  List<StockPriceEntity> findAllBySymbolAndTypeAndMarketTimeGreaterThanEqualOrderByMarketTime(String symbol, String type, LocalDateTime systemTime);
  Optional<StockPriceEntity> findBySymbolAndTypeAndRegularMarketTime(String symbol, String type, Long regularMarketTime);
}
