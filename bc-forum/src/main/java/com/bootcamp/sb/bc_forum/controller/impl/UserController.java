package com.bootcamp.sb.bc_forum.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_forum.controller.UserOperation;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.model.dto.CommentDto;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;
import com.bootcamp.sb.bc_forum.model.dto.mapper.DTOMapper;
import com.bootcamp.sb.bc_forum.service.CommentService;
import com.bootcamp.sb.bc_forum.service.PostService;
import com.bootcamp.sb.bc_forum.service.UserService;


@RestController
@RequestMapping(value = "/api/v1")
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;

  @Autowired
  private PostService postService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private DTOMapper dtoMapper;

  @Override
  public List<UserDTO> getUsers() {
    List<PostDto> posts = postService.getPosts();
    List<CommentDto> comments = commentService.getComments();
    return userService.getUsers().stream()
    .map(e -> dtoMapper.mapUser(e, posts, comments))
    .collect(Collectors.toList());

  }
}
