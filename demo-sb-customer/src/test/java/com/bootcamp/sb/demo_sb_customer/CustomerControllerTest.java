package com.bootcamp.sb.demo_sb_customer;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.controller.impl.CustomerController;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.sb.demo_sb_customer.service.CustomerService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @MockitoBean // @MockBean , old version
    private CustomerService customerService;

    // ! @WebMvcTest inject MockMvc Bean into context
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCustomers() throws Exception {
        CustomerEntity customerEntity1 =
                CustomerEntity.builder().name("test1").age(10).id(99L).build();
        CustomerEntity customerEntity2 =
                CustomerEntity.builder().name("test2").age(12).id(100L).build();

        List<CustomerEntity> serviceResult =
                Arrays.asList(customerEntity1, customerEntity2);

        Mockito.when(customerService.getCustomers()).thenReturn(serviceResult);

        ResultActions result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/customers"));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("000000"))
                .andExpect(jsonPath("$.message").value("Success."))
                .andExpect(jsonPath("$.data[0].name").value("test1"))
                .andExpect(jsonPath("$.data[0].age").value(10))
                .andExpect(jsonPath("$.data[0].id").value(99L))
                .andExpect(jsonPath("$.data[1].name").value("test2"))
                .andExpect(jsonPath("$.data[1].age").value(12))
                .andExpect(jsonPath("$.data[1].id").value(100L));

        String json = result.andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        JavaType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, CustomerEntity.class);
        JavaType responseType = objectMapper.getTypeFactory()
                .constructParametricType(ApiResp.class, collectionType);

        ApiResp<List<CustomerEntity>> apiResp =
                objectMapper.readValue(json, responseType);
        // ApiResp<List<CustomerEntity>> apiResp = new ObjectMapper().readValue(
        // json, new TypeReference<ApiResp<List<CustomerEntity>>>() {});

        MatcherAssert.assertThat(apiResp.getCode(), Matchers.is("000000"));
        MatcherAssert.assertThat(apiResp.getMessage(), Matchers.is("Success."));

        List<CustomerEntity> customerEntities = apiResp.getData();

        MatcherAssert.assertThat(customerEntities, Matchers.containsInAnyOrder( //
                Matchers.hasProperty("name", Matchers.equalTo("test2")), //
                Matchers.hasProperty("name", Matchers.equalTo("test1")) //
        ));
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerEntity customerEntity =
                CustomerEntity.builder().name("test1").age(10).id(99L).build();
        Mockito.when(customerService.createCustomer(any(CustomerEntity.class)))
                .thenReturn(customerEntity);

        String requestJson =
                new ObjectMapper().writeValueAsString(customerEntity);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson));

        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("000000"))
                .andExpect(jsonPath("$.message").value("Success."))
                .andExpect(jsonPath("$.data.name").value("test1"))
                .andExpect(jsonPath("$.data.age").value(10))
                .andExpect(jsonPath("$.data.id").value(99L));

        String json = result.andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        JavaType javaType = objectMapper.getTypeFactory()
                .constructParametricType(ApiResp.class, CustomerEntity.class);

        ApiResp<CustomerEntity> apiResp =
                objectMapper.readValue(json, javaType);

        MatcherAssert.assertThat(apiResp.getCode(), Matchers.is("000000"));
        MatcherAssert.assertThat(apiResp.getMessage(), Matchers.is("Success."));

        CustomerEntity customerEntityResult = apiResp.getData();

        MatcherAssert.assertThat(customerEntityResult.getName(),
                Matchers.is("test1"));

        //verify(customerService, times(1)).createCustomer(customerEntity);
    }
}
