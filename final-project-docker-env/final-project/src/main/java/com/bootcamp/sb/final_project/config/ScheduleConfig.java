package com.bootcamp.sb.final_project.config;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.final_project.model.TStockRecordType;
import com.bootcamp.sb.final_project.service.HolidayCheckerService;
import com.bootcamp.sb.final_project.service.TStockPriceService;
import com.bootcamp.sb.final_project.service.TStockService;
import com.bootcamp.sb.final_project.service.YahooFinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ScheduleConfig {
  @Autowired
  private YahooFinanceService yahooFinanceService;

  @Autowired
  private HolidayCheckerService holidayCheckerService;

  @Autowired
  private TStockService tStockService;

  @Autowired
  private TStockPriceService tStockPriceService;

  @Scheduled(cron = "0 */5 * * * *")
  public void fetchStockPrice5M() throws JsonProcessingException {
    this.yahooFinanceService.fetchStockPrice(TStockRecordType.FIVE_MINUTES);
  }

  @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Hong_Kong")
  public void fetchStockPriceD() throws JsonProcessingException {
    this.yahooFinanceService.fetchStockPrice(TStockRecordType.DAILY);
  }

  @Scheduled(cron = "0 0 0 * * 6", zone = "Asia/Hong_Kong")
  public void fetchStockPriceW() throws JsonProcessingException {
    this.yahooFinanceService.fetchStockPrice(TStockRecordType.WEEKLY);
  }

  @Scheduled(cron = "0 0 0 1 * *", zone = "Asia/Hong_Kong")
  public void fetchStockPriceM() throws JsonProcessingException {
    this.yahooFinanceService.fetchStockPrice(TStockRecordType.MONTHLY);
  }

  @Scheduled(cron = "0 0 8 * * *", zone = "Asia/Hong_Kong")
  public void resetSystemTime() throws JsonProcessingException {
    LocalDate today = LocalDate.now();
    if (!this.holidayCheckerService.isHoliday(today)) {
      List<String> symbols = this.tStockService.findAllSymbols();
      for (String symbol : symbols) {
        this.tStockPriceService.setSystemTime(symbol, today);
      }
    }
  }

  @Scheduled(cron = "0 0 17 * * *", zone = "Asia/Hong_Kong")
  public void fetchOHLCData() throws JsonProcessingException {
    this.yahooFinanceService.fetchStockPriceHistory();
  }
}
