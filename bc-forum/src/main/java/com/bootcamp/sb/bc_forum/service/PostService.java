package com.bootcamp.sb.bc_forum.service;

import java.util.List;
import com.bootcamp.sb.bc_forum.dto.PostDTO;
import com.bootcamp.sb.bc_forum.entity.PostEntity;

public interface PostService {
  List<PostDTO> getPostByUserId(Long userId);
  List<PostDTO> getAllPosts();
  PostDTO createPost(Long userId, PostEntity postEntity);
  void deletePostById(Long id);
}
