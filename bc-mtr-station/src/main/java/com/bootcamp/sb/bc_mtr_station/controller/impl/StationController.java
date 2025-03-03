package com.bootcamp.sb.bc_mtr_station.controller.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_mtr_station.codewave.ApiResp;
import com.bootcamp.sb.bc_mtr_station.codewave.SysCode;
import com.bootcamp.sb.bc_mtr_station.controller.StationOperation;
import com.bootcamp.sb.bc_mtr_station.dto.EarliestTrainDTO;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;
import com.bootcamp.sb.bc_mtr_station.service.StationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/api/v1")
public class StationController implements StationOperation {
  @Autowired
  private StationService stationService;

  @Override
  public ApiResp<List<StationEntity>> getAllStations() {
    return ApiResp.<List<StationEntity>>builder().sysCode(SysCode.OK)
        .data(this.stationService.getAllStations()).build();
  }

  @Override
  public ApiResp<List<StationEntity>> getStationsByLine(String lineName) {
    return ApiResp.<List<StationEntity>>builder().sysCode(SysCode.OK)
        .data(this.stationService.getStationsByLine(lineName)).build();
  }

  @Override
  public ApiResp<StationEntity> createStation(String lineName,
      String stationName, String previousStationName, String nextStationName) {
    return ApiResp.<StationEntity>builder().sysCode(SysCode.OK)
        .data(this.stationService.createStation(lineName, stationName,
            previousStationName, nextStationName))
        .build();
  }

  @Override
  public ApiResp<Void> deleteStation(String stationName) {
    this.stationService.deleteStation(stationName);
    return ApiResp.<Void>builder().sysCode(SysCode.OK).build();
  }

  @Override
  public ApiResp<Map<String, List<StationEntity>>> getStationMap() {
    return ApiResp.<Map<String, List<StationEntity>>>builder()
        .sysCode(SysCode.OK).data(this.stationService.getStationMap()).build();
  }

  @Override
  public ApiResp<EarliestTrainDTO> getEarliestTrain(String station) throws JsonProcessingException {
    return ApiResp.<EarliestTrainDTO>builder().sysCode(SysCode.OK)
        .data(this.stationService.getEarliestTrain(station)).build();
  }
}
