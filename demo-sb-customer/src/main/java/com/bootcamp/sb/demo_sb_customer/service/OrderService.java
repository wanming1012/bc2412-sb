package com.bootcamp.sb.demo_sb_customer.service;

import java.util.List;
import com.bootcamp.sb.demo_sb_customer.entity.OrderEntity;

public interface OrderService {
  OrderEntity createOrder(Long customerId, OrderEntity orderEntity);
  OrderEntity getOrder(Long id);
  List<OrderEntity> getOrders();
  void deleteOrder(Long id);
} 