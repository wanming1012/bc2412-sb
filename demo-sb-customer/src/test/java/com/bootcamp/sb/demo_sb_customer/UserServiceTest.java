package com.bootcamp.sb.demo_sb_customer;

import static org.mockito.ArgumentMatchers.anyString;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sb.demo_sb_customer.entity.mapper.EntityMapper;
import com.bootcamp.sb.demo_sb_customer.model.dto.UserDto;
import com.bootcamp.sb.demo_sb_customer.repository.UserRepository;
import com.bootcamp.sb.demo_sb_customer.service.AddressService;
import com.bootcamp.sb.demo_sb_customer.service.CompanyService;
import com.bootcamp.sb.demo_sb_customer.service.GeoService;
import com.bootcamp.sb.demo_sb_customer.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressService addressService;

    @Mock
    private GeoService geoService;

    @Mock
    private CompanyService companyService;

    @Mock
    private RestTemplate restTemplate;

    @Spy
    private EntityMapper entityMapper;

    @InjectMocks
    private UserServiceImpl userService;


    //@Test
    void testImportUsers() throws Exception {
        UserDto user1 = UserDto.builder().id(1L).name("name1")
                .username("username1").email("user1@test.com").phone("phone1")
                .website("website1")
                .address(UserDto.Address.builder().street("street1")
                        .suite("suite1").city("city1").zipcode("zipcode1")
                        .geo(UserDto.Address.Geo.builder().latitude(1.1)
                                .longitude(1.2).build())
                        .build())
                .company(UserDto.Company.builder().name("company1")
                        .catchPhrase("catch phrase1").bs("bs1").build())
                .build();

        UserDto user2 = UserDto.builder().id(2L).name("name2")
                .username("username2").email("user2@test.com").phone("phone2")
                .website("website2")
                .address(UserDto.Address.builder().street("street2")
                        .suite("suite2").city("city2").zipcode("zipcode2")
                        .geo(UserDto.Address.Geo.builder().latitude(2.1)
                                .longitude(2.2).build())
                        .build())
                .company(UserDto.Company.builder().name("company2")
                        .catchPhrase("catch phrase2").bs("bs2").build())
                .build();

        UserDto[] users = new UserDto[] {user1, user2};

        Mockito.when(restTemplate.getForObject(anyString(),
                Mockito.eq(UserDto[].class))).thenReturn(users);

        // GeoEntity geoEntity =
        // GeoEntity.builder().id(1L).latitude(1.1).longitude(1.2).build();
        // Mockito.when(geoService.createGeo(any(GeoEntity.class)))
        // .thenReturn(geoEntity);

        // AddressEntity addressEntity =
        // AddressEntity.builder().id(1L).street("street").suite("suite")
        // .city("city").zipcode("zipcode").geoEntity(geoEntity).build();
        // Mockito
        // .when(addressService.createAddress(anyLong(), any(AddressEntity.class)))
        // .thenReturn(addressEntity);

        // CompanyEntity companyEntity = CompanyEntity.builder().id(1L).name("name")
        // .catchPhrase("catchPhrase").bs("bs").build();
        // Mockito.when(companyService.createCompany(any(CompanyEntity.class)))
        // .thenReturn(companyEntity);

        // Mockito.doNothing().when(userEntity)

        List<UserDto> returnUsers = userService.importUsers();

        MatcherAssert.assertThat(returnUsers,
                Matchers.containsInAnyOrder(user2, user1));

    }

}
