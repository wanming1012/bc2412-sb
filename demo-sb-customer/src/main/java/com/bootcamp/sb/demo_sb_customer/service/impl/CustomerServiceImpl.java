package com.bootcamp.sb.demo_sb_customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_customer.codewave.BusinessException;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.sb.demo_sb_customer.repository.CustomerRepository;
import com.bootcamp.sb.demo_sb_customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public CustomerEntity createCustomer(CustomerEntity customer) {
    return this.customerRepository.save(customer);
  }

  @Override
  public CustomerEntity getCustomer(Long id) {
    return this.customerRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
  }

  @Override
  public List<CustomerEntity> getCustomers() {
    return this.customerRepository.findAll();
  }

  @Override
  public void delete(Long id) {
    if (!this.customerRepository.existsById(id))
      throw BusinessException.of(SysCode.ID_NOT_FOUND);

    this.customerRepository.deleteById(id);
  }
}
