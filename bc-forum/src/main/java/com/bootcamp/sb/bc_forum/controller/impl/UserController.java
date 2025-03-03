package com.bootcamp.sb.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_forum.codewave.ApiResp;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.controller.UserOperation;
import com.bootcamp.sb.bc_forum.dto.UserCommentsDTO;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.dto.UserPostsCommentsDTO;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;
import com.bootcamp.sb.bc_forum.service.UserService;


@RestController
@RequestMapping(value = "/api/v1")
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;

  @Override
  public ApiResp<UserDTO> getUserById(Long id) {
    return ApiResp.<UserDTO>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.getUser(id))
      .build();
  }

  @Override
  public ApiResp<List<UserDTO>> getAllUsers() {
    return ApiResp.<List<UserDTO>>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.getUsers())
      .build();
  }

  @Override
  public ApiResp<UserDTO> replaceUser(UserDto userDto) {
    return ApiResp.<UserDTO>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.replaceUser(userDto))
      .build();
  }

  @Override
  public ApiResp<List<UserPostsCommentsDTO>> getAllPostsAndComments() {
    return ApiResp.<List<UserPostsCommentsDTO>>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.getAllPostsAndComments())
      .build();
  }

  @Override
  public ApiResp<UserCommentsDTO> getAllUserComments(Long id) {
    return ApiResp.<UserCommentsDTO>builder()
      .sysCode(SysCode.OK)
      .data(this.userService.getAllUserComments(id))
      .build();
  }
}
