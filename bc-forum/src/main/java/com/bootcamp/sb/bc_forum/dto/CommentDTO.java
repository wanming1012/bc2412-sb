package com.bootcamp.sb.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDTO {
  private Long id;
  private String name;
  private String email;
  private String body;
  private Long postId;
}
