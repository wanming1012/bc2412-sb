package com.bootcamp.sb.bc_forum.model.dto;

import lombok.Getter;

@Getter
public class CommentDto {
  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;
}
