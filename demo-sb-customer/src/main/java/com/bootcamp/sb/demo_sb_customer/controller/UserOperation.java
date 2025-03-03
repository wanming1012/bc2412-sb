package com.bootcamp.sb.demo_sb_customer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.dto.UserDTO;
import com.bootcamp.sb.demo_sb_customer.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserOperation {
  @GetMapping(value = "/users/import")
  ApiResp<List<UserDTO>> importUsers() throws JsonProcessingException;

  @GetMapping(value = "/users")
  ApiResp<List<UserEntity>> getUsers();

  @GetMapping(value = "/user/{id}")
  ApiResp<UserEntity> getUser(@PathVariable Long id);

  @PostMapping(value = "/user")
  ApiResp<UserEntity> createUser(@RequestParam Long addressId, @RequestParam Long companyId, @RequestBody UserEntity userEntity);
}
