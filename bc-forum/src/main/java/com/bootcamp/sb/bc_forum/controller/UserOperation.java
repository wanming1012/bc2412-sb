package com.bootcamp.sb.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.sb.bc_forum.dto.UserDTO;

public interface UserOperation {
  @GetMapping(value = "/users")
  List<UserDTO> getUsers();
}
