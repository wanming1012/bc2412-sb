package com.bootcamp.sb.bc_mtr_station.dto;

import java.sql.Date;
import java.util.List;
import com.bootcamp.sb.bc_mtr_station.model.Signal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignalDTO {
  private String line;
  private Signal signal;

  @JsonProperty(value = "delayStation")
  private List<String> delayStations;

  @JsonProperty(value = "curr_time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private Date currTime;

  @JsonProperty(value = "sys_time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private Date sysTime;
}
