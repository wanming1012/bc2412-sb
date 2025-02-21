package com.bootcamp.sb.bc_mtr_station.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sb.bc_mtr_station.model.dto.ScheduleDto;
import com.bootcamp.sb.bc_mtr_station.service.MTRService;

@Service
public class MTRServiceImpl implements MTRService {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.schedules}")
  private String schedulesEndpoint;

  @Override
  public ScheduleDto fetchTrainSchedule(String line, String station) {
    String uri = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(schedulesEndpoint).queryParam("line", line)
        .queryParam("sta", station).build().toUriString();

    int times = 0;
    final int MAX_TIMES = 10;
    ScheduleDto scheduleDto = null;
    while (true) {
      try {
        scheduleDto =
            this.restTemplate.getForObject(uri, ScheduleDto.class);
        break;
      } catch (HttpClientErrorException.TooManyRequests e) {
        times++;
        if (times >= MAX_TIMES) {
          throw new RuntimeException(e);
        }
      }
    }
    return scheduleDto;
  }

}
