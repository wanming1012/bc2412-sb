package com.bootcamp.sb.bc_forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;

public interface CommentOperation {
  @GetMapping(value = "/comments")
  CommentDTO getComments(@RequestParam Long id);
}
