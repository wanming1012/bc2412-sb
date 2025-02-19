package com.bootcamp.sb.bc_forum.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.bc_forum.codewave.BusinessException;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.dto.PostDTO;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import com.bootcamp.sb.bc_forum.entity.UserEntity;
import com.bootcamp.sb.bc_forum.entity.mapper.EntityMapper;
import com.bootcamp.sb.bc_forum.repository.CommentRepository;
import com.bootcamp.sb.bc_forum.repository.PostRepository;
import com.bootcamp.sb.bc_forum.repository.UserRepository;
import com.bootcamp.sb.bc_forum.service.PostService;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private EntityMapper entityMapper;

  @Override
  public List<PostDTO> getPostByUserId(Long userId) {
    UserEntity userEntity = this.userRepository.findById(userId)
        .orElseThrow(() -> BusinessException.of(SysCode.USER_ID_NOT_FOUND));
    return this.postRepository.findByUserEntity(userEntity).stream()
        .map(e -> this.entityMapper.map(e)).collect(Collectors.toList());
  }

  @Override
  public List<PostDTO> getAllPosts() {
    return this.postRepository.findAll().stream()
        .map(e -> this.entityMapper.map(e)).collect(Collectors.toList());
  }

  @Override
  public PostDTO createPost(Long userId, PostEntity postEntity) {
    UserEntity userEntity = this.userRepository.findById(userId)
        .orElseThrow(() -> BusinessException.of(SysCode.USER_ID_NOT_FOUND));
    postEntity.setUserEntity(userEntity);
    return this.entityMapper.map(this.postRepository.save(postEntity));
  }

  @Override
  public void deletePostById(Long id) {
    PostEntity postEntity = this.postRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.POST_ID_NOT_FOUND));

    this.commentRepository.findByPostEntity(postEntity).stream()
         .forEach(e -> this.commentRepository.deleteById(e.getId()));
    this.postRepository.deleteById(id);
  }
}
