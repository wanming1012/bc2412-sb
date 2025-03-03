package com.bootcamp.sb.demo_sb_customer.controller.impl;

import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.controller.CustomerOperation;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.sb.demo_sb_customer.service.CustomerService;

@RestController
@RequestMapping(value = "/api/v1")
public class CustomerController implements CustomerOperation {
  //@Autowired
  CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public ApiResp<CustomerEntity> createCustomer(CustomerEntity customer) {
    return ApiResp.<CustomerEntity>builder()
        .sysCode(SysCode.OK)
        .data(customerService.createCustomer(customer))
        .build();
  }

  @Override
  public ApiResp<CustomerEntity> getCustomer(Long id) {
    return ApiResp.<CustomerEntity>builder()
        .sysCode(SysCode.OK)
        .data(customerService.getCustomer(id))
        .build();
  }

  @Override
  public ApiResp<List<CustomerEntity>> getCustomers() {
    return ApiResp.<List<CustomerEntity>>builder()
        .sysCode(SysCode.OK)
        .data(customerService.getCustomers())
        .build();
  }

  @Override
  public ApiResp<Void> deleteCustomer(Long id) {
    customerService.delete(id);
    return ApiResp.<Void>builder()
        .sysCode(SysCode.OK)
        .build();
  }

  @Override
  public ApiResp<CustomerEntity> getCustomer(String name) {
    return ApiResp.<CustomerEntity>builder()
        .sysCode(SysCode.OK)
        .data(customerService.getCustomer(name))
        .build();
  }
}
