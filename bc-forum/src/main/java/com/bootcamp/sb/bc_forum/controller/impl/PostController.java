package com.bootcamp.sb.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_forum.codewave.ApiResp;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.controller.PostOperation;
import com.bootcamp.sb.bc_forum.dto.PostDTO;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import com.bootcamp.sb.bc_forum.service.PostService;

@RestController
@RequestMapping(value = "/api/v1")
public class PostController implements PostOperation {
  @Autowired
  private PostService postService;

  @Override
  public ApiResp<List<PostDTO>> getAllPosts() {
    return ApiResp.<List<PostDTO>>builder().sysCode(SysCode.OK)
        .data(postService.getAllPosts()).build();
  }

  @Override
  public ApiResp<List<PostDTO>> getPostByUserId(Long userId) {
    return ApiResp.<List<PostDTO>>builder().sysCode(SysCode.OK)
        .data(postService.getPostByUserId(userId)).build();
  }

  @Override
  public ApiResp<PostDTO> createPost(Long userId, PostEntity postEntity) {
    return ApiResp.<PostDTO>builder().sysCode(SysCode.OK)
        .data(this.postService.createPost(userId, postEntity)).build();
  }

  @Override
  public ApiResp<Void> deletePostById(Long id) {
    this.postService.deletePostById(id);
    return ApiResp.<Void>builder().sysCode(SysCode.OK).build();
  }
}
