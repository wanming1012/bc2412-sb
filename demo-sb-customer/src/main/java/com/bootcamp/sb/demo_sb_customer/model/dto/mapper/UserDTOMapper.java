package com.bootcamp.sb.demo_sb_customer.model.dto.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.sb.demo_sb_customer.dto.UserDTO;
import com.bootcamp.sb.demo_sb_customer.model.dto.UserDto;

@Component
public class UserDTOMapper {
  public UserDTO map(UserDto dto) {
    return UserDTO.builder()
          .id(dto.getId())
          .username(dto.getUsername())
          .email(dto.getEmail())
          .address(dto.getAddress() == null ? null : UserDTO.Address.builder()
              .street(dto.getAddress().getStreet())
              .suite(dto.getAddress().getSuite())
              .city(dto.getAddress().getCity())
              .zipcode(dto.getAddress().getZipcode())
              .geo(dto.getAddress().getGeo() == null ? null : UserDTO.Address.Geo.builder()
                  .latitude(dto.getAddress().getGeo().getLatitude())
                  .longitude(dto.getAddress().getGeo().getLongitude())
                  .build()
              ).build()
          ).build();
  }
  
}
