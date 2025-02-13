package com.bootcamp.sb.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_forum.controller.PostOperation;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;
import com.bootcamp.sb.bc_forum.service.PostService;

@RestController
@RequestMapping(value = "/api/v1")
public class PostController implements PostOperation {
  @Autowired
  private PostService postService;

  @Override
  public List<PostDto> getPosts() {
    return postService.getPosts();
  }
}
