package com.bootcamp.sb.bc_forum.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.bc_forum.codewave.BusinessException;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import com.bootcamp.sb.bc_forum.entity.mapper.EntityMapper;
import com.bootcamp.sb.bc_forum.repository.CommentRepository;
import com.bootcamp.sb.bc_forum.repository.PostRepository;
import com.bootcamp.sb.bc_forum.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private EntityMapper entityMapper;

  @Override
  public List<CommentDTO> getCommentsByPostId(Long postId) {
    PostEntity postEntity = this.postRepository.findById(postId)
        .orElseThrow(() -> BusinessException.of(SysCode.POST_ID_NOT_FOUND));
    return this.commentRepository.findByPostEntity(postEntity).stream()
        .map(e -> this.entityMapper.map(e)).collect(Collectors.toList());
  }

  @Override
  public List<CommentDTO> getAllComments() {
    return this.commentRepository.findAll().stream()
        .map(e -> this.entityMapper.map(e)).collect(Collectors.toList());
  }

  @Override
  public CommentDTO createComment(Long postId, CommentEntity commentEntity) {
    PostEntity postEntity = this.postRepository.findById(postId)
        .orElseThrow(() -> BusinessException.of(SysCode.POST_ID_NOT_FOUND));

    commentEntity.setPostEntity(postEntity);

    return this.entityMapper.map(this.commentRepository.save(commentEntity));
  }

  @Override
  public CommentDTO modifyBodyById(Long id, String body) {
    CommentEntity commentEntity = this.commentRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.COMMENT_ID_NOT_FOUND));

    commentEntity.setBody(body);

    return this.entityMapper.map(this.commentRepository.save(commentEntity));
  }
}
