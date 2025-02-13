package com.bootcamp.sb.demo_sb_customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

//! Spring: Convention over configuration

@Entity // define table structure by java
@Table(name = "Customers")
@Getter
public class CustomerEntity {
  @Id // PK
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private Long id;
  @Column(name = "customer_name")
  private String name;
  @Column(name = "customer_age")
  private Integer age;
}
