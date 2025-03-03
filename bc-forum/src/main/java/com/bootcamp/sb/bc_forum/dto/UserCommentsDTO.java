package com.bootcamp.sb.bc_forum.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCommentsDTO {
  private Long id;
  private String username;
  private List<Comment> comments;

  @Getter
  @Builder
  public static class Comment {
    private String name;
    private String email;
    private String body;
  }
}
