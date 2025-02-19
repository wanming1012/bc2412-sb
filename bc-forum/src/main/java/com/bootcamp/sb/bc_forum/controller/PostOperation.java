package com.bootcamp.sb.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sb.bc_forum.codewave.ApiResp;
import com.bootcamp.sb.bc_forum.dto.PostDTO;
import com.bootcamp.sb.bc_forum.entity.PostEntity;

public interface PostOperation {
  @GetMapping(value = "/posts/{user_id}")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<PostDTO>> getPostByUserId(@PathVariable(value = "user_id") Long userId);

  @GetMapping(value = "/posts")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<PostDTO>> getAllPosts();

  @PostMapping(value = "/post/{user_id}")
  @ResponseStatus(value = HttpStatus.CREATED)
  ApiResp<PostDTO> createPost(@PathVariable(value = "user_id") Long userId, @RequestBody PostEntity postEntity);

  @DeleteMapping(value = "/post/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<Void> deletePostById(@PathVariable Long id);
}
