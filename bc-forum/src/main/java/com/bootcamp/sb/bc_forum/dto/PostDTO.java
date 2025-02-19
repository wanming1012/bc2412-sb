package com.bootcamp.sb.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDTO {
  private Long id;
  private String title;
  private String body;
  private String username;
}
