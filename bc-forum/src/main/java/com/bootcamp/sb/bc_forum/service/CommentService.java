package com.bootcamp.sb.bc_forum.service;

import java.util.List;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;

public interface CommentService {
  List<CommentDTO> getCommentsByPostId(Long postId);
  List<CommentDTO> getAllComments();
  CommentDTO createComment(Long postId, CommentEntity commentEntity);
  CommentDTO modifyBodyById(Long id, String body);
}
