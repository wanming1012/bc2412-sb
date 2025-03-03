package com.bootcamp.sb.bc_mtr_station.model.dto;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ScheduleDto {
  private Integer status;
  private String message;
  private String url;

  @JsonProperty(value = "sys_time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private Date sysTime;

  @JsonProperty(value = "curr_time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private Date currTime;

  @JsonProperty(value = "isdelay")
  private String isDelay;

  private Map<String, Station> data;

  @Getter
  public static class Station {
    @JsonProperty(value = "sys_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd hh:mm:ss")
    private Date sysTime;

    @JsonProperty(value = "curr_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd hh:mm:ss")
    private Date currTime;

    @JsonProperty(value = "UP")
    private List<Platform> upPlatforms;

    @JsonProperty(value = "DOWN")
    private List<Platform> downPlatforms;

    @Getter
    public static class Platform {
      private String ttnt;
      private String valid;

      @JsonFormat(shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd hh:mm:ss")
      private Date time;

      private String source;
      private String dest;
      private String seq;
    }
  }
}
