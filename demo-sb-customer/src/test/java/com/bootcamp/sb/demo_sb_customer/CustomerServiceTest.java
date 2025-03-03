package com.bootcamp.sb.demo_sb_customer;

import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import org.assertj.core.api.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.sb.demo_sb_customer.repository.CustomerRepository;
import com.bootcamp.sb.demo_sb_customer.service.CustomerService;

@SpringBootTest
public class CustomerServiceTest {
  @Autowired
  private CustomerService customerService;

  @MockBean
  private CustomerRepository customerRepository;

  @Test
  public void testGetAllCustomers() {
    CustomerEntity customer1 =
        CustomerEntity.builder().id(1L).name("name1").age(10).build();

    CustomerEntity customer2 =
        CustomerEntity.builder().id(2L).name("name2").age(20).build();

    List<CustomerEntity> customers = Arrays.asList(customer1, customer2);

    Mockito.when(customerRepository.findAll()).thenReturn(customers);

    List<CustomerEntity> result = this.customerService.getCustomers();

    // MatcherAssert.assertThat(result,
    //     Matchers.containsInAnyOrder(
    //         Matchers.<CustomerEntity>hasProperty("id", Matchers.equalTo(2L)),
    //         Matchers.<CustomerEntity>hasProperty("id", Matchers.equalTo(1L))));
  }
}
