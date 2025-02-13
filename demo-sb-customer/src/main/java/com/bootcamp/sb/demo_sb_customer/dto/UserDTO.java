package com.bootcamp.sb.demo_sb_customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
  private Long id;
  private String username;
  private String email;
  private Address address;

  @Getter
  @Builder
  @AllArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
 
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Geo {
      @JsonProperty(value = "x")
      private Double latitude;
      @JsonProperty(value = "y")
      private Double longitude;
    }
  }
}
