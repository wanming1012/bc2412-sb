package com.bootcamp.sb.demo_sb_customer.service;

import java.util.List;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;

public interface CustomerService {
  CustomerEntity createCustomer(CustomerEntity customer);
  CustomerEntity getCustomer(Long id);
  List<CustomerEntity> getCustomers();
  void delete(Long id);
}
