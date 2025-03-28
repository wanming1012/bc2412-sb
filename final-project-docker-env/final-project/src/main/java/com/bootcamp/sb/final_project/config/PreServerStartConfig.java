package com.bootcamp.sb.final_project.config;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.final_project.entity.TStockEntity;
import com.bootcamp.sb.final_project.lib.RedisManager;
import com.bootcamp.sb.final_project.repository.TStockRepository;
import com.bootcamp.sb.final_project.service.YahooFinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  private TStockRepository tStockRepository;

  @Autowired
  private YahooFinanceService yahooFinanceService;

  @Autowired
  private RedisManager redisManager;

  @Override
  public void run(String... args) throws Exception {
    List<String> symbols = List.of("0005.HK", "0388.HK", "0522.HK");
    addTSocks(symbols);
    deleteRedisRecords(symbols);
    loadOHLCData();
  }

  private void addTSocks(List<String> symbols) {
    for (String symbol : symbols) {
      Optional<TStockEntity> dbTStockEntity =
          this.tStockRepository.findBySymbol(symbol);
      if (!dbTStockEntity.isPresent()) {
        TStockEntity tStockEntity =
            TStockEntity.builder().symbol(symbol).build();
        tStockEntity = this.tStockRepository.save(tStockEntity);
      }
    }
  }

  private void deleteRedisRecords(List<String> symbols) {
    this.redisManager.delete("STOCK-LIST");
    this.redisManager.delete("SYSDATE-*");
    this.redisManager.delete("5M-*");
    this.redisManager.delete("D-*");
    this.redisManager.delete("W-*");
    this.redisManager.delete("M-*");

    for (String symbol : symbols) {
      this.redisManager.delete("*" + symbol);
      // this.redisManager.delete("SYSDATE-" + symbol);
      // this.redisManager.delete("5M-" + symbol);
      // this.redisManager.delete("D-" + symbol);
      // this.redisManager.delete("W-" + symbol);
      // this.redisManager.delete("M-" + symbol);
      // this.redisManager.delete("D-OHLC-" + symbol);
      // this.redisManager.delete("W-OHLC-" + symbol);
      // this.redisManager.delete("M-OHLC-" + symbol);
    }
  }

  private void loadOHLCData() throws JsonProcessingException {
    this.yahooFinanceService.fetchStockPriceHistory();
  }
}

