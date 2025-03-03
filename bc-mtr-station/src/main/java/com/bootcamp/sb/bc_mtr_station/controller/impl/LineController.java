package com.bootcamp.sb.bc_mtr_station.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_mtr_station.codewave.ApiResp;
import com.bootcamp.sb.bc_mtr_station.codewave.SysCode;
import com.bootcamp.sb.bc_mtr_station.controller.LineOperation;
import com.bootcamp.sb.bc_mtr_station.dto.SignalDTO;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;
import com.bootcamp.sb.bc_mtr_station.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/api/v1")
public class LineController implements LineOperation {
  @Autowired
  private LineService lineService;

  @Override
  public ApiResp<List<LineEntity>> getAllLines() {
    return ApiResp.<List<LineEntity>>builder().sysCode(SysCode.OK)
        .data(this.lineService.getAllLines()).build();
  }

  @Override
  public ApiResp<SignalDTO> getLineSignal(String lineName) throws JsonProcessingException {
    return ApiResp.<SignalDTO>builder().sysCode(SysCode.OK)
        .data(this.lineService.getLineSignal(lineName)).build();
  }

  @Override
  public ApiResp<List<SignalDTO>> getAllLineSignals() throws JsonProcessingException {
    return ApiResp.<List<SignalDTO>>builder().sysCode(SysCode.OK)
        .data(this.lineService.getAllLineSignals()).build();
  }
}
