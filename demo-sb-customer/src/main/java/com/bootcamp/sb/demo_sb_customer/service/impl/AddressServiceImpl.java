package com.bootcamp.sb.demo_sb_customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_customer.codewave.BusinessException;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.entity.AddressEntity;
import com.bootcamp.sb.demo_sb_customer.entity.GeoEntity;
import com.bootcamp.sb.demo_sb_customer.repository.AddressRepository;
import com.bootcamp.sb.demo_sb_customer.repository.GeoRepository;
import com.bootcamp.sb.demo_sb_customer.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private GeoRepository geoRepository;

  @Override
  public AddressEntity getAddress(Long id) {
    return this.addressRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
  }

  @Override
  public List<AddressEntity> getAddresses() {
    return this.addressRepository.findAll();
  }

  @Override
  public AddressEntity createAddress(Long geoId, AddressEntity addressEntity) {
    GeoEntity geoEntity = this.geoRepository.findById(geoId)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
    addressEntity.setGeoEntity(geoEntity);
    return this.addressRepository.save(addressEntity);
  }
}
