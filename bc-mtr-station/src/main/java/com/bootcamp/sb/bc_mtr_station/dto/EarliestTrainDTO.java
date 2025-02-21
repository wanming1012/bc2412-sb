package com.bootcamp.sb.bc_mtr_station.dto;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarliestTrainDTO {
  @JsonProperty(value = "curr_time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private Date currTime;

  @JsonProperty(value = "sys_time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private Date sysTime;

  private String currentStation;

  private List<Train> trains;

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Train {
    private String destination;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd hh:mm:ss")
    private Date arrivalTime;

    private String direction;
  }
}
