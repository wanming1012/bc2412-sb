package com.bootcamp.sb.bc_mtr_station.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.bc_mtr_station.codewave.BusinessException;
import com.bootcamp.sb.bc_mtr_station.codewave.SysCode;
import com.bootcamp.sb.bc_mtr_station.dto.EarliestTrainDTO;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;
import com.bootcamp.sb.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;
import com.bootcamp.sb.bc_mtr_station.model.dto.ScheduleDto;
import com.bootcamp.sb.bc_mtr_station.model.dto.ScheduleDto.Station.Platform;
import com.bootcamp.sb.bc_mtr_station.repository.LineRepository;
import com.bootcamp.sb.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.sb.bc_mtr_station.repository.StationRepository;
import com.bootcamp.sb.bc_mtr_station.service.MTRService;
import com.bootcamp.sb.bc_mtr_station.service.StationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class StationServiceImpl implements StationService {
  @Autowired
  private StationRepository stationRepository;

  @Autowired
  private LineStationRepository lineStationRepository;

  @Autowired
  private LineRepository lineRepository;

  @Autowired
  private MTRService mtrService;

  @Override
  public List<StationEntity> getAllStations() {
    return this.stationRepository.findAll();
  }

  @Override
  public List<StationEntity> getStationsByLine(String lineName) {
    LineEntity lineEntity = lineRepository.findByName(lineName)
        .orElseThrow(() -> BusinessException.of(SysCode.LINE_CODE_NOT_FOUND));
    return this.lineStationRepository.findByLine(lineEntity).stream()
        .map(e -> e.getCurrentStation()).collect(Collectors.toList());
  }

  @Override
  public Map<String, List<StationEntity>> getStationMap() {
    return lineStationRepository.findAll().stream()
        .collect(Collectors.groupingBy(e -> e.getLine().getName(),
            Collectors.mapping(LineStationEntity::getCurrentStation,
                Collectors.toList())));
  }

  @Override
  public StationEntity createStation(String lineName, String stationName,
      String previousStationName, String nextStationName) {
    // check if line exists
    LineEntity lineEntity = lineRepository.findByName(lineName)
        .orElseThrow(() -> BusinessException.of(SysCode.LINE_CODE_NOT_FOUND));

    // check if new station already exists
    if (stationRepository.findByName(stationName).isPresent()) {
      throw BusinessException.of(SysCode.INVALID_INPUT);
    }

    // check if previous and next stations exist
    StationEntity previousStationEntity = null;
    if (previousStationName != null && !previousStationName.isEmpty()) {
      previousStationEntity =
          stationRepository.findByName(previousStationName).orElseThrow(
              () -> BusinessException.of(SysCode.STATION_CODE_NOT_FOUND));
    }

    StationEntity nextStationEntity = null;
    if (nextStationName != null && !nextStationName.isEmpty()) {
      nextStationEntity =
          stationRepository.findByName(nextStationName).orElseThrow(
              () -> BusinessException.of(SysCode.STATION_CODE_NOT_FOUND));
    }

    // check if fist station
    LineStationEntity previousLineStationEntity = null;
    if (previousStationEntity == null && nextStationEntity != null) {
      // current station will be the first station, check if next station is original first station
      previousLineStationEntity = lineStationRepository
          .findByLineAndCurrentStation(lineEntity, nextStationEntity)
          .orElseThrow(() -> BusinessException.of(SysCode.INVALID_INPUT));
    }

    // check if last station
    LineStationEntity nextLineStationEntity = null;
    if (previousStationEntity != null && nextStationEntity == null) {
      // current station will be the last station, check if previous station is original last station
      nextLineStationEntity = lineStationRepository
          .findByLineAndCurrentStation(lineEntity, previousStationEntity)
          .orElseThrow(() -> BusinessException.of(SysCode.INVALID_INPUT));
    }

    // check if only one station
    if (previousStationEntity == null && nextStationEntity == null) {
      // check if line has only one station
      if (lineStationRepository.findByLine(lineEntity).size() != 0) {
        throw BusinessException.of(SysCode.INVALID_INPUT);
      }
    }

    StationEntity newStationEntity =
        StationEntity.builder().name(stationName).build();
    newStationEntity = stationRepository.save(newStationEntity);

    LineStationEntity newLineStationEntity = LineStationEntity.builder()
        .line(lineEntity).currentStation(newStationEntity)
        .previousStation(previousStationEntity).nextStation(nextStationEntity)
        .build();
    lineStationRepository.save(newLineStationEntity);

    // update previous and next line-station records
    if (previousLineStationEntity != null) {
      previousLineStationEntity.setPreviousStation(newStationEntity);
      lineStationRepository.save(previousLineStationEntity);
    }

    if (nextLineStationEntity != null) {
      nextLineStationEntity.setNextStation(newStationEntity);
      lineStationRepository.save(nextLineStationEntity);
    }

    return newStationEntity;
  }

  @Override
  public void deleteStation(String stationName) {
    // check if station exists
    StationEntity stationEntity =
        stationRepository.findByName(stationName).orElseThrow(
            () -> BusinessException.of(SysCode.STATION_CODE_NOT_FOUND));

    List<LineStationEntity> lineStationEntities =
        this.lineStationRepository.findByCurrentStation(stationEntity);
    for (LineStationEntity lineStationEntity : lineStationEntities) {
      StationEntity previousStationEntity =
          lineStationEntity.getPreviousStation();
      StationEntity nextStationEntity = lineStationEntity.getNextStation();

      if (previousStationEntity != null) {
        LineStationEntity previousLineStationEntity = lineStationRepository
            .findByLineAndCurrentStation(lineStationEntity.getLine(),
                previousStationEntity)
            .orElseThrow(() -> BusinessException.of(SysCode.INVALID_INPUT));
        previousLineStationEntity.setNextStation(nextStationEntity);
        lineStationRepository.save(previousLineStationEntity);
      }

      if (nextStationEntity != null) {
        LineStationEntity nextLineStationEntity = lineStationRepository
            .findByLineAndCurrentStation(lineStationEntity.getLine(),
                nextStationEntity)
            .orElseThrow(() -> BusinessException.of(SysCode.INVALID_INPUT));
        nextLineStationEntity.setPreviousStation(previousStationEntity);
        lineStationRepository.save(nextLineStationEntity);
      }

      lineStationRepository.delete(lineStationEntity);
    }
    stationRepository.delete(stationEntity);
  }

  @Override
  public EarliestTrainDTO getEarliestTrain(String stationName) throws JsonProcessingException{
    StationEntity stationEntity =
        stationRepository.findByName(stationName).orElseThrow(
            () -> BusinessException.of(SysCode.STATION_CODE_NOT_FOUND));

    List<LineStationEntity> lineStationEntities =
        this.lineStationRepository.findByCurrentStation(stationEntity);

    List<EarliestTrainDTO.Train> trains = new ArrayList<>();
    Date currTime = null, sysTime = null;
    for (LineStationEntity lineStationEntity : lineStationEntities) {
      String lineName = lineStationEntity.getLine().getName();
      ScheduleDto schedule =
          this.mtrService.fetchTrainSchedule(lineName, stationName);

      currTime = schedule.getCurrTime();
      sysTime = schedule.getSysTime();

      if (schedule.getStatus() == 0) {
        throw BusinessException.of(SysCode.MTR_SERVICE_ERROR);
      }

      List<Platform> platforms =
          schedule.getData().get(lineName + "-" + stationName).getUpPlatforms();
      if (platforms != null) {
        Platform platform = platforms.get(0); // assume earliest train is the first one
        trains.add(
            EarliestTrainDTO.Train.builder().destination(platform.getDest())
                .arrivalTime(platform.getTime()).direction("UP").build());
      }

      platforms = schedule.getData().get(lineName + "-" + stationName)
          .getDownPlatforms();
      if (platforms != null) {
        Platform platform = platforms.get(0); // assume earliest train is the first one
        trains.add(
            EarliestTrainDTO.Train.builder().destination(platform.getDest())
                .arrivalTime(platform.getTime()).direction("DOWN").build());
      }
    }
    return EarliestTrainDTO.builder().currTime(currTime).sysTime(sysTime)
        .currentStation(stationName).trains(trains).build();
  }

}
