package com.bootcamp.sb.bc_forum.service;

import java.util.List;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;

public interface UserService {
  UserDTO getUser(Long id);
  List<UserDTO> getUsers();
  UserDTO replaceUser(UserDto userDto);
}
