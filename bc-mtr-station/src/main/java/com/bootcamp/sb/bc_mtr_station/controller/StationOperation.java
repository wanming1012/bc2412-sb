package com.bootcamp.sb.bc_mtr_station.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sb.bc_mtr_station.codewave.ApiResp;
import com.bootcamp.sb.bc_mtr_station.dto.EarliestTrainDTO;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StationOperation {
  @GetMapping(value = "/stations")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<StationEntity>> getAllStations();

  @GetMapping(value = "/stations", params = "line")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<StationEntity>> getStationsByLine(
      @RequestParam(value = "line") String lineName);

  @GetMapping(value = "/stations/map")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<Map<String, List<StationEntity>>> getStationMap();

  @GetMapping(value = "/station/earliest")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<EarliestTrainDTO> getEarliestTrain(
      @RequestParam(value = "sta") String stationName) throws JsonProcessingException;

  @PostMapping(value = "/station")
  @ResponseStatus(value = HttpStatus.CREATED)
  ApiResp<StationEntity> createStation(
      @RequestParam(value = "line") String lineName,
      @RequestParam(value = "sta") String stationName,
      @RequestParam(value = "prev_sta") String previousStationName,
      @RequestParam(value = "next_sta") String nextStationName);

  @DeleteMapping(value = "/station")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<Void> deleteStation(@RequestParam(value = "sta") String stationName);
}
