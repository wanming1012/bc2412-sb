package com.bootcamp.sb.bc_forum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String username;
  private String email;
  private String phone;
  private String website;

  // // address
  // private String street;
  // private String suite;
  // private String city;
  // private String zipcode;

  // // geo
  // private Double latitude;
  // private Double longitude;

  @OneToOne
  @JoinColumn(name = "address_id")
  private AddressEntity addressEntity;

  @OneToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity companyEntity;
}
