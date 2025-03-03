package com.bootcamp.sb.bc_mtr_station.service;

import java.util.List;
import com.bootcamp.sb.bc_mtr_station.dto.SignalDTO;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LineService {
  List<LineEntity> getAllLines();
  LineEntity createLine(String lineName);
  SignalDTO getLineSignal(String lineName) throws JsonProcessingException;
  List<SignalDTO> getAllLineSignals() throws JsonProcessingException;
}
