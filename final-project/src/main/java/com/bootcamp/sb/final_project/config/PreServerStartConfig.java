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
    for (String symbol : symbols) {
      this.redisManager.delete("SYSDATE-" + symbol);
      this.redisManager.delete("5M-" + symbol);
    }
  }

  private void loadOHLCData() throws JsonProcessingException{
    this.yahooFinanceService.fetchStockPriceHistory();
  }
}

