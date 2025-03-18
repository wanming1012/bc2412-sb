package com.bootcamp.sb.final_project.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sb.final_project.lib.RedisManager;
import com.bootcamp.sb.final_project.model.dto.HolidayDto;
import com.bootcamp.sb.final_project.service.HolidayCheckerService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class HolidayCheckerServiceImpl implements HolidayCheckerService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisManager redisManager;

  public boolean isHoliday(LocalDate date) throws JsonProcessingException {
    HolidayDto[] holidays =
        this.redisManager.get("HOLIDAYS", HolidayDto[].class);

    int currentYear = LocalDate.now().getYear();

    if (holidays == null || holidays[0].getDate().getYear() != currentYear) {
      String url = "https://date.nager.at/api/v3/PublicHolidays/" + currentYear + "/HK";
      holidays = restTemplate.getForObject(url, HolidayDto[].class);
      this.redisManager.set("HOLIDAYS", holidays, Duration.ofDays(365));
    }

    return Arrays.stream(holidays)
        .anyMatch(h -> h.getDate().equals(date));
  }
}