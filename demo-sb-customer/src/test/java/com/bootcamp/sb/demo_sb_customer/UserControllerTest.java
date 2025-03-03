package com.bootcamp.sb.demo_sb_customer;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.controller.impl.UserController;
import com.bootcamp.sb.demo_sb_customer.dto.UserDTO;
import com.bootcamp.sb.demo_sb_customer.entity.AddressEntity;
import com.bootcamp.sb.demo_sb_customer.entity.CompanyEntity;
import com.bootcamp.sb.demo_sb_customer.entity.GeoEntity;
import com.bootcamp.sb.demo_sb_customer.entity.UserEntity;
import com.bootcamp.sb.demo_sb_customer.model.dto.UserDto;
import com.bootcamp.sb.demo_sb_customer.model.dto.mapper.UserDTOMapper;
import com.bootcamp.sb.demo_sb_customer.service.UserService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
        @MockitoBean
        private UserService userService;

        @SpyBean
        // @MockitoSpyBean
        private UserDTOMapper userDTOMapper;

        @Autowired
        private MockMvc mockMvc;

        @Test
        void testImportUser() throws Exception {
                UserDto inputUser1 = UserDto.builder().id(1L).name("name1")
                                .username("username1").email("user1@test.com")
                                .phone("phone1").website("website1")
                                .address(UserDto.Address.builder()
                                                .street("street1")
                                                .suite("suite1").city("city1")
                                                .zipcode("zipcode1")
                                                // .geo(UserDto.Address.Geo.builder().latitude(1.1)
                                                // .longitude(1.2).build())
                                                .build())
                                .company(UserDto.Company.builder()
                                                .name("company1")
                                                .catchPhrase("catch phrase1")
                                                .bs("bs1").build())
                                .build();

                UserDto inputUser2 = UserDto.builder().id(2L).name("name2")
                                .username("username2").email("user2@test.com")
                                .phone("phone2").website("website2")
                                .address(UserDto.Address.builder()
                                                .street("street2")
                                                .suite("suite2").city("city2")
                                                .zipcode("zipcode2")
                                                .geo(UserDto.Address.Geo
                                                                .builder()
                                                                .latitude(2.1)
                                                                .longitude(2.2)
                                                                .build())
                                                .build())
                                .company(UserDto.Company.builder()
                                                .name("company2")
                                                .catchPhrase("catch phrase2")
                                                .bs("bs2").build())
                                .build();

                List<UserDto> inputUsers =
                                Arrays.asList(inputUser1, inputUser2);

                Mockito.when(userService.importUsers()).thenReturn(inputUsers);

                ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                                .get("/api/v1/users/import"));

                String json = result.andReturn().getResponse()
                                .getContentAsString();

                ObjectMapper objectMapper = new ObjectMapper();

                JavaType collectionType = objectMapper.getTypeFactory()
                                .constructCollectionType(List.class,
                                                UserDTO.class);
                JavaType responseType = objectMapper.getTypeFactory()
                                .constructParametricType(ApiResp.class,
                                                collectionType);

                ApiResp<List<UserDTO>> apiResp =
                                objectMapper.readValue(json, responseType);

                MatcherAssert.assertThat(apiResp.getCode(),
                                Matchers.is("000000"));
                MatcherAssert.assertThat(apiResp.getMessage(),
                                Matchers.is("Success."));

                List<UserDTO> users = apiResp.getData();

                MatcherAssert.assertThat(users, Matchers.containsInAnyOrder( //
                                Matchers.hasProperty("username",
                                                Matchers.equalTo("username1")), //
                                Matchers.hasProperty("username", Matchers
                                                .equalTo("username2"))));

                UserDTO user1 = UserDTO.builder().id(1L).username("username1")
                                .email("user1@test.com")
                                .address(UserDTO.Address.builder()
                                                .street("street1")
                                                .suite("suite1").city("city1")
                                                .zipcode("zipcode1")
                                                // .geo(UserDTO.Address.Geo.builder()
                                                // .latitude(1.1).longitude(1.2).build())
                                                .build())
                                .build();
                UserDTO user2 = UserDTO.builder().id(2L).username("username2")
                                .email("user2@test.com")
                                .address(UserDTO.Address.builder()
                                                .street("street2")
                                                .suite("suite2").city("city2")
                                                .zipcode("zipcode2")
                                                .geo(UserDTO.Address.Geo
                                                                .builder()
                                                                .latitude(2.1)
                                                                .longitude(2.2)
                                                                .build())
                                                .build())
                                .build();

                // List<UserDTO> expectedUsers = Arrays.asList(user1, user2);
                MatcherAssert.assertThat(users,
                                Matchers.containsInAnyOrder(user2, user1));
        }

        @Test
        void testGetAllUsers() throws Exception{
                UserEntity user1 = UserEntity.builder().id(1L)
                                .name("name1").username("username1")
                                .email("user1@test.com").phone("phone1")
                                .website("website1")
                                .addressEntity(AddressEntity.builder().id(1L)
                                                .street("street1")
                                                .suite("suite1").city("city1")
                                                .zipcode("zipcode1")
                                                .geoEntity(GeoEntity.builder()
                                                                .latitude(1.1)
                                                                .longitude(1.2)
                                                                .build())
                                                .build())
                                .companyEntity(CompanyEntity.builder().id(1L)
                                                .name("company1")
                                                .catchPhrase("catch phrase1")
                                                .bs("bs1").build())
                                .build();

                UserEntity user2 = UserEntity.builder().id(2L)
                                .name("name2").username("username2")
                                .email("user2@test.com").phone("phone2")
                                .website("website2")
                                .addressEntity(AddressEntity.builder().id(2L)
                                                .street("street2")
                                                .suite("suite2").city("city2")
                                                .zipcode("zipcode2")
                                                .geoEntity(GeoEntity.builder()
                                                                .latitude(2.1)
                                                                .longitude(2.2)
                                                                .build())
                                                .build())
                                .companyEntity(CompanyEntity.builder()
                                                .id(2L)
                                                .name("company2")
                                                .catchPhrase("catch phrase2")
                                                .bs("bs2").build())
                                .build();

                List<UserEntity> users =
                                Arrays.asList(user1, user2);
                Mockito.when(userService.getUsers()).thenReturn(users);

                ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                                .get("/api/v1/users"));

                String json = result.andReturn().getResponse()
                                .getContentAsString();

                ObjectMapper objectMapper = new ObjectMapper();

                JavaType collectionType = objectMapper.getTypeFactory()
                                .constructCollectionType(List.class,
                                                UserEntity.class);
                JavaType responseType = objectMapper.getTypeFactory()
                                .constructParametricType(ApiResp.class,
                                                collectionType);

                ApiResp<List<UserEntity>> apiResp =
                                objectMapper.readValue(json, responseType);

                MatcherAssert.assertThat(apiResp.getCode(),
                                Matchers.is("000000"));
                MatcherAssert.assertThat(apiResp.getMessage(),
                                Matchers.is("Success."));

                MatcherAssert.assertThat(apiResp.getData(), Matchers.containsInAnyOrder(users.toArray()));
        };
}
