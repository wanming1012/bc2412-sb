package com.bootcamp.sb.bc_forum.model.dto;

import lombok.Getter;

// ! Data Transfer Object
// This DTO is for deserialization (JSON -> OBJECT)
@Getter
public class UserDto {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Getter
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    public static class Geo {
      private Double lat;
      private Double lng;
    }
  }

  @Getter
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}