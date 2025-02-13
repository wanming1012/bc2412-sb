package com.bootcamp.sb.bc_forum.service;

import java.util.List;
import com.bootcamp.sb.bc_forum.model.dto.CommentDto;

public interface CommentService {
  List<CommentDto> getComments();
}
