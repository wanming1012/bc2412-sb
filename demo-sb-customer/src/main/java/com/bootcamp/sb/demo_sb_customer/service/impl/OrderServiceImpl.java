package com.bootcamp.sb.demo_sb_customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_customer.codewave.BusinessException;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.sb.demo_sb_customer.entity.OrderEntity;
import com.bootcamp.sb.demo_sb_customer.repository.CustomerRepository;
import com.bootcamp.sb.demo_sb_customer.repository.OrderRepository;
import com.bootcamp.sb.demo_sb_customer.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public OrderEntity createOrder(Long customerId, OrderEntity orderEntity) {
    CustomerEntity customerEntity = this.customerRepository.findById(customerId)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
    orderEntity.setCustomerEntity(customerEntity);
    return this.orderRepository.save(orderEntity);
  }

  @Override
  public OrderEntity getOrder(Long id) {
    return this.orderRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
  }

  @Override
  public List<OrderEntity> getOrders() {
    return this.orderRepository.findAll();
  }

  @Override
  public void deleteOrder(Long id) {
    if (!this.orderRepository.existsById(id))
      throw BusinessException.of(SysCode.ID_NOT_FOUND);

    this.orderRepository.deleteById(id);
  }
}
