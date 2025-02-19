package com.bootcamp.sb.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.bc_forum.codewave.ApiResp;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;

public interface CommentOperation {
  @GetMapping(value = "/comments", params = "post_id")
  ApiResp<List<CommentDTO>> getCommentsByPostId(@RequestParam(value = "post_id") Long postId);

  @GetMapping(value = "/comments")
  ApiResp<List<CommentDTO>> getAllComments();

  @PostMapping(value = "/comment")
  ApiResp<CommentDTO> createComment(@RequestParam(value = "post_id") Long postId, @RequestBody CommentEntity commentEntity);

  @PatchMapping(value = "/comment")
  ApiResp<CommentDTO> modifyBodyById(@RequestParam Long id, @RequestParam String body);
}
