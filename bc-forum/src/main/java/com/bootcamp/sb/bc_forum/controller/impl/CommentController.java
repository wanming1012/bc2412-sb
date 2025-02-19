package com.bootcamp.sb.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.bc_forum.codewave.ApiResp;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.controller.CommentOperation;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;
import com.bootcamp.sb.bc_forum.service.CommentService;

@RestController
@RequestMapping(value = "/api/v1")
public class CommentController implements CommentOperation {
  @Autowired
  private CommentService commentService;

  @Override
  public ApiResp<List<CommentDTO>> getCommentsByPostId(Long postId) {
    return ApiResp.<List<CommentDTO>>builder().sysCode(SysCode.OK)
        .data(this.commentService.getCommentsByPostId(postId)).build();
  }

  @Override
  public ApiResp<List<CommentDTO>> getAllComments() {
    return ApiResp.<List<CommentDTO>>builder().sysCode(SysCode.OK)
        .data(this.commentService.getAllComments()).build();
  }

  @Override
  public ApiResp<CommentDTO> createComment(Long postId, CommentEntity commentEntity) {
    return ApiResp.<CommentDTO>builder().sysCode(SysCode.OK)
        .data(this.commentService.createComment(postId, commentEntity)).build();
  }

  @Override
  public ApiResp<CommentDTO> modifyBodyById(Long id, String body) {
    return ApiResp.<CommentDTO>builder().sysCode(SysCode.OK)
        .data(this.commentService.modifyBodyById(id, body)).build();
  }
}
