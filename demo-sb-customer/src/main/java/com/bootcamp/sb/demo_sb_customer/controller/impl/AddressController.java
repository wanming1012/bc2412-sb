package com.bootcamp.sb.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.controller.AddressOperation;
import com.bootcamp.sb.demo_sb_customer.entity.AddressEntity;
import com.bootcamp.sb.demo_sb_customer.service.AddressService;

@RestController
@RequestMapping(value = "/api/v1")
public class AddressController implements AddressOperation {
  @Autowired
  private AddressService addressService;

  @Override
  public ApiResp<AddressEntity> getAddress(Long id) {
    return ApiResp.<AddressEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.addressService.getAddress(id))
      .build();
  }

  @Override
  public ApiResp<List<AddressEntity>> getAddresses() {
    return ApiResp.<List<AddressEntity>>builder()
      .sysCode(SysCode.OK)
      .data(this.addressService.getAddresses())
      .build();
  }

  @Override
  public ApiResp<AddressEntity> createAddress(Long geoId, AddressEntity addressEntity) {
    return ApiResp.<AddressEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.addressService.createAddress(geoId, addressEntity))
      .build();
  }
}
