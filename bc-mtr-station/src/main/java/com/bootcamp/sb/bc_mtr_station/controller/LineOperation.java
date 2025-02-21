package com.bootcamp.sb.bc_mtr_station.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sb.bc_mtr_station.codewave.ApiResp;
import com.bootcamp.sb.bc_mtr_station.dto.SignalDTO;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;

public interface LineOperation {
  @GetMapping(value = "/lines")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<LineEntity>> getAllLines();

  @GetMapping(value = "/line/signal")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<SignalDTO> getLineSignal(@RequestParam(value = "line") String lineName);
  

  @GetMapping(value = "/line/signals")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<SignalDTO>> getAllLineSignals();
}
