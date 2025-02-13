package com.bootcamp.sb.demo_sb_customer.controller.impl;

import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.controller.OrderOperation;
import com.bootcamp.sb.demo_sb_customer.entity.OrderEntity;
import com.bootcamp.sb.demo_sb_customer.service.OrderService;

@RestController
@RequestMapping(value = "/api/v1")
public class OrderController implements OrderOperation {
  //@Autowired
  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public ApiResp<OrderEntity> getOrder(Long id) {
    return ApiResp.<OrderEntity>builder()
        .sysCode(SysCode.OK)
        .data(orderService.getOrder(id))
        .build();
  }

  @Override
  public ApiResp<OrderEntity> createOrder(Long customerId, @RequestBody OrderEntity orderEntity) {
    return ApiResp.<OrderEntity>builder()
        .sysCode(SysCode.OK)
        .data(this.orderService.createOrder(customerId, orderEntity))
        .build();
  }

  @Override
  public ApiResp<List<OrderEntity>> getOrders() {
    return ApiResp.<List<OrderEntity>>builder()
        .sysCode(SysCode.OK)
        .data(this.orderService.getOrders())
        .build();
  }

  @Override
  public ApiResp<Void> deleteOrder(Long id) {
    this.orderService.deleteOrder(id);
    return ApiResp.<Void>builder()
        .sysCode(SysCode.OK)
        .build();
  }
}
