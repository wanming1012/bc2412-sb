package com.bootcamp.sb.demo_sb_customer.model.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ! Data Transfer Object
// This DTO is for deserialization (JSON -> OBJECT)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Getter
  @Builder
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geo {
      @JsonProperty(value = "lat")
      private Double latitude;
      @JsonProperty(value = "lng")
      private Double longitude;
    }
  }

  @Getter
  @Builder
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}