package com.bootcamp.demo.demo_sb_helloworld;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShoppingMall {
  private String name;
  private int area;
  private Cinema cinema;
  private List<String> shopCategory;
  // getters and setters

  @Getter
  @Builder
  public static class Cinema {
    private String name;
    private LocalDate openedDate;
    private List<Film> releasedFilms;
    // getters and setters

    @Getter
    @Builder
    public static class Film {
      private String name;
      private LocalDate releaseDate;
      // getters and setters
    }
  }
}
