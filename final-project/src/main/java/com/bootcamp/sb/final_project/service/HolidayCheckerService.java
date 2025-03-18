package com.bootcamp.sb.final_project.service;

import java.time.LocalDate;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface HolidayCheckerService {
  boolean isHoliday(LocalDate date) throws JsonProcessingException;
}
