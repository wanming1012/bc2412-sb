package com.bootcamp.sb.demo_sb_customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_customer.codewave.BusinessException;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.entity.GeoEntity;
import com.bootcamp.sb.demo_sb_customer.repository.GeoRepository;
import com.bootcamp.sb.demo_sb_customer.service.GeoService;

@Service
public class GeoServiceImpl implements GeoService {
  @Autowired
  private GeoRepository geoRepository;

  @Override
  public GeoEntity getGeo(Long id) {
    return this.geoRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
  }

  @Override
  public List<GeoEntity> getGeoes() {
    return this.geoRepository.findAll();
  }

  @Override
  public GeoEntity createGeo(GeoEntity geoEntity) {
    return this.geoRepository.save(geoEntity);
  }
}
