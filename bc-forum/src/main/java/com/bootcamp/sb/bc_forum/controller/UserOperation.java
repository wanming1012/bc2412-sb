package com.bootcamp.sb.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sb.bc_forum.codewave.ApiResp;
import com.bootcamp.sb.bc_forum.dto.UserCommentsDTO;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.dto.UserPostsCommentsDTO;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;

public interface UserOperation {
  @GetMapping(value = "/user")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<UserDTO> getUserById(@RequestParam Long id);
  
  @GetMapping(value = "/users")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<UserDTO>> getAllUsers();

  @PutMapping(value = "/user")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<UserDTO> replaceUser(@RequestBody UserDto userDto);

  @GetMapping(value = "/users/all")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<UserPostsCommentsDTO>> getAllPostsAndComments();

  @GetMapping(value = "/user/comments")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<UserCommentsDTO> getAllUserComments(@RequestParam Long id);
}
