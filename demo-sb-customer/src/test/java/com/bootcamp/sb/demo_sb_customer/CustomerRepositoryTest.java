package com.bootcamp.sb.demo_sb_customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.sb.demo_sb_customer.repository.CustomerRepository;

@DataJpaTest
public class CustomerRepositoryTest {
  @Autowired
  CustomerRepository customerRepository;

  @Test
  void testSave() {
    CustomerEntity customer =
        CustomerEntity.builder().name("name1").age(10).build();

    CustomerEntity customerEntity = customerRepository.save(customer);
    assertEquals("name1", customerEntity.getName());
    assertEquals(10, customerEntity.getAge());
    assertEquals(1L, customerEntity.getId());

    System.out.println(customerEntity);

    customer = CustomerEntity.builder().name("name2").age(20).build();
    customerEntity = customerRepository.save(customer);
    assertEquals("name2", customerEntity.getName());
    assertEquals(20, customerEntity.getAge());
    assertEquals(2L, customerEntity.getId());

    System.out.println(customerEntity);

    List<CustomerEntity> customers = customerRepository.findAll();
    assertEquals(2, customers.size());

    System.out.println(customers);
  }
}
