package com.bootcamp.sb.demo_sb_customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bootcamp.sb.demo_sb_customer.model.dto.UserDto;

@ExtendWith(MockitoExtension.class)
public class DtoMapperTest {
  @Test
  public void testMap() {
    UserDto inputUser =
        UserDto.builder().id(1L).name("name1").username("username1")
            .email("user1@test.com").phone("phone1").website("website1")
            .address(UserDto.Address.builder().street("street1").suite("suite1")
                .city("city1").zipcode("zipcode1")
                .geo(UserDto.Address.Geo.builder().latitude(1.1)
                .longitude(1.2).build())
                .build())
            .company(UserDto.Company.builder().name("company1")
                .catchPhrase("catch phrase1").bs("bs1").build())
            .build();
        
        
  }
}
