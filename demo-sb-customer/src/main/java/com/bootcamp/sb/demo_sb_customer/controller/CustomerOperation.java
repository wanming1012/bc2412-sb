package com.bootcamp.sb.demo_sb_customer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;

public interface CustomerOperation {
  @GetMapping(value = "/customer/{id}")
  ApiResp<CustomerEntity> getCustomer(@PathVariable Long id);

  @PostMapping(value = "/customer")
  ApiResp<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer);

  @GetMapping(value = "/customers")
  ApiResp<List<CustomerEntity>> getCustomers();

  @DeleteMapping(value = "/customer/{id}")
  ApiResp<Void> deleteCustomer(@PathVariable Long id);
}
