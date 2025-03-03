package com.bootcamp.sb.demo_sb_customer.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;

public interface CustomerOperation {
  @GetMapping(value = "/customer/{id}")
  @ResponseStatus(HttpStatus.OK)
  ApiResp<CustomerEntity> getCustomer(@PathVariable Long id);

  @PostMapping(value = "/customer")
  @ResponseStatus(HttpStatus.CREATED)
  ApiResp<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer);

  @GetMapping(value = "/customers")
  ApiResp<List<CustomerEntity>> getCustomers();

  @DeleteMapping(value = "/customer/{id}")
  ApiResp<Void> deleteCustomer(@PathVariable Long id);

  @GetMapping(value = "/customer")
  ApiResp<CustomerEntity> getCustomer(@RequestParam String name);
}
