package com.bootcamp.sb.demo_sb_customer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.entity.AddressEntity;

public interface AddressOperation {
  @GetMapping(value = "/address/{id}")
  ApiResp<AddressEntity> getAddress(@PathVariable Long id);

  @GetMapping(value = "/addresses")
  ApiResp<List<AddressEntity>> getAddresses();
  
  @PostMapping(value = "/address")
  ApiResp<AddressEntity> createAddress(@RequestParam Long geoId, @RequestBody AddressEntity addressEntity);
}
