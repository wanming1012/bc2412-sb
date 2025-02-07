package com.bootcamp.sb.demo_sb_restful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cat {
  private Long id;  
  private String name;
  private Integer age;
}
