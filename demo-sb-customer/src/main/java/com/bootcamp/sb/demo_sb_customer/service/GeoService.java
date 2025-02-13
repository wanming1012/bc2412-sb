package com.bootcamp.sb.demo_sb_customer.service;

import java.util.List;
import com.bootcamp.sb.demo_sb_customer.entity.GeoEntity;

public interface GeoService {
  GeoEntity getGeo(Long id);
  List<GeoEntity> getGeoes();
  GeoEntity createGeo(GeoEntity geoEntity);
}
