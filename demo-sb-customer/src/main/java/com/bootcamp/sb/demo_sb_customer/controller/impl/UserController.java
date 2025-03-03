package com.bootcamp.sb.demo_sb_customer.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.controller.UserOperation;
import com.bootcamp.sb.demo_sb_customer.dto.UserDTO;
import com.bootcamp.sb.demo_sb_customer.entity.UserEntity;
import com.bootcamp.sb.demo_sb_customer.model.dto.mapper.UserDTOMapper;
import com.bootcamp.sb.demo_sb_customer.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;

  @Autowired
  private UserDTOMapper userDTOMapper;
  
  @Override
  public ApiResp<List<UserDTO>> importUsers() throws JsonProcessingException{
    List<UserDTO> userDTOs = userService.importUsers().stream()
      .map(e -> userDTOMapper.map(e))
      .collect(Collectors.toList());
    return ApiResp.<List<UserDTO>>builder()
      .sysCode(SysCode.OK)
      .data(userDTOs)
      .build();
  }

  @Override
  public ApiResp<UserEntity> getUser(Long id) {
    return ApiResp.<UserEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.getUser(id))
      .build();
  }

  @Override
  public ApiResp<List<UserEntity>> getUsers() {
    return ApiResp.<List<UserEntity>>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.getUsers())
      .build();
  }

  @Override
  public ApiResp<UserEntity> createUser(Long addressId, Long companyId, UserEntity userEntity) {
    return ApiResp.<UserEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.createUser(addressId, companyId, userEntity))
      .build();
  }
}
