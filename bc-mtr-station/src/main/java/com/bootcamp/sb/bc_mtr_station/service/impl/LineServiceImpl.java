package com.bootcamp.sb.bc_mtr_station.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.bc_mtr_station.codewave.BusinessException;
import com.bootcamp.sb.bc_mtr_station.codewave.SysCode;
import com.bootcamp.sb.bc_mtr_station.dto.SignalDTO;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;
import com.bootcamp.sb.bc_mtr_station.model.Signal;
import com.bootcamp.sb.bc_mtr_station.model.dto.ScheduleDto;
import com.bootcamp.sb.bc_mtr_station.repository.LineRepository;
import com.bootcamp.sb.bc_mtr_station.service.LineService;
import com.bootcamp.sb.bc_mtr_station.service.MTRService;
import com.bootcamp.sb.bc_mtr_station.service.StationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class LineServiceImpl implements LineService {
  @Autowired
  private LineRepository lineRepository;

  @Autowired
  private StationService stationService;

  @Autowired
  private MTRService mtrService;

  @Override
  public List<LineEntity> getAllLines() {
    return this.lineRepository.findAll();
  }

  @Override
  public LineEntity createLine(String lineName) {
    if (this.lineRepository.findByName(lineName).isPresent()) {
      throw BusinessException.of(SysCode.LINE_CODE_NOT_FOUND);
    }

    LineEntity newLineEntity = LineEntity.builder().name(lineName).build();
    return this.lineRepository.save(newLineEntity);
  }

  @Override
  public SignalDTO getLineSignal(String lineName) throws JsonProcessingException{
    List<StationEntity> stations =
        this.stationService.getStationsByLine(lineName);

    int numOfDelayStation = 0;
    List<String> delayStations = new ArrayList<>();
    Date sysTime = null, currTime = null;
    for (StationEntity station : stations) {
      ScheduleDto schedule =
          this.mtrService.fetchTrainSchedule(lineName, station.getName());
      sysTime = schedule.getSysTime();
      currTime = schedule.getCurrTime();

      if (schedule.getStatus() == 0) {
        throw BusinessException.of(SysCode.MTR_SERVICE_ERROR);
      }

      if (schedule.getIsDelay() == "Y") {
        numOfDelayStation++;
        delayStations.add(station.getName());
      }
    }

    Signal signal;
    if (numOfDelayStation == 0)
      signal = Signal.GREEN;
    else if (numOfDelayStation == 1)
      signal = Signal.YELLOW;
    else
      signal = Signal.RED;

    return SignalDTO.builder().line(lineName).signal(signal)
        .delayStations(delayStations).currTime(currTime).sysTime(sysTime)
        .build();
  }

  @Override
  public List<SignalDTO> getAllLineSignals() throws JsonProcessingException {
    List<SignalDTO> signals = new ArrayList<>();
    List<LineEntity> lines = getAllLines();
    for (LineEntity line : lines) {
      signals.add(this.getLineSignal(line.getName()));
    }

    return signals;
  }

}
