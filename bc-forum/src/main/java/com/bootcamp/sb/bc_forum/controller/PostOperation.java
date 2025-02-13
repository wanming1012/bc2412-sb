package com.bootcamp.sb.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;

public interface PostOperation {
  @GetMapping(value = "/posts")
  List<PostDto> getPosts();
}
