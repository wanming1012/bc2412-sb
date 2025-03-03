package com.bootcamp.sb.bc_mtr_station.service;

import java.util.List;
import java.util.Map;
import com.bootcamp.sb.bc_mtr_station.dto.EarliestTrainDTO;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StationService {
  List<StationEntity> getAllStations();

  List<StationEntity> getStationsByLine(String lineName);

  Map<String, List<StationEntity>> getStationMap();

  StationEntity createStation(String lineName, String stationName,
      String previousStationName, String nextStationName);

  void deleteStation(String stationName);

  EarliestTrainDTO getEarliestTrain(String stationName) throws JsonProcessingException;
}
