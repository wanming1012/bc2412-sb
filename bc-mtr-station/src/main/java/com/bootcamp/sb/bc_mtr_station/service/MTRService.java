package com.bootcamp.sb.bc_mtr_station.service;

import com.bootcamp.sb.bc_mtr_station.model.dto.ScheduleDto;

public interface MTRService {
    ScheduleDto fetchTrainSchedule(String line, String station);
}
