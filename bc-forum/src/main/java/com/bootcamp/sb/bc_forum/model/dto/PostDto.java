package com.bootcamp.sb.bc_forum.model.dto;

import lombok.Getter;

@Getter
public class PostDto {
  private Long userId;
  private Long id;
  private String title;
  private String body;
}
