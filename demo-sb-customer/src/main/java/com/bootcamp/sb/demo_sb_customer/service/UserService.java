package com.bootcamp.sb.demo_sb_customer.service;

import java.util.List;
import com.bootcamp.sb.demo_sb_customer.entity.UserEntity;
import com.bootcamp.sb.demo_sb_customer.model.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {
  List<UserDto> importUsers() throws JsonProcessingException;
  UserEntity getUser(Long id);
  List<UserEntity> getUsers();
  UserEntity createUser(Long addressId, Long companyId, UserEntity userEntity);
}
