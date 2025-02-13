package com.bootcamp.sb.demo_sb_customer.service;

import java.util.List;
import com.bootcamp.sb.demo_sb_customer.entity.AddressEntity;

public interface AddressService {
  AddressEntity getAddress(Long id);
  List<AddressEntity> getAddresses();
  AddressEntity createAddress(Long geoId, AddressEntity addressEntity);
}
