package com.bootcamp.sb.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;
import com.bootcamp.sb.bc_forum.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.users}")
  private String userEndpoint;

  @Override
  public List<UserDto> getUsers() {
    String uri = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(userEndpoint).build().toUriString();

    return Arrays.asList(this.restTemplate.getForObject(uri, UserDto[].class));
  }
}
