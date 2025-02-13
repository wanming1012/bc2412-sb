package com.bootcamp.sb.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_forum.codewave.BusinessException;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.controller.CommentOperation;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;
import com.bootcamp.sb.bc_forum.model.dto.CommentDto;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;
import com.bootcamp.sb.bc_forum.model.dto.mapper.DTOMapper;
import com.bootcamp.sb.bc_forum.service.CommentService;
import com.bootcamp.sb.bc_forum.service.PostService;
import com.bootcamp.sb.bc_forum.service.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class CommentController implements CommentOperation {
  @Autowired
  private UserService userService;

  @Autowired
  private PostService postService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private DTOMapper dtoMapper;

  @Override
  public CommentDTO getComments(Long id) {
    UserDto user = userService.getUsers().stream().filter(e -> e.getId() == id).findFirst().orElseThrow(() -> BusinessException.of(SysCode.USER_ID_NOT_FOUND));
    List<PostDto> posts = postService.getPosts();
    List<CommentDto> comments = commentService.getComments();
    return dtoMapper.mapComment(user, posts, comments);
  }
}
