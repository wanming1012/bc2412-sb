package com.bootcamp.sb.bc_forum.service;

import java.util.List;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;

public interface PostService {
  List<PostDto> getPosts();
}
