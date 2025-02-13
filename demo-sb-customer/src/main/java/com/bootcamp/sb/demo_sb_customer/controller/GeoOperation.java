package com.bootcamp.sb.demo_sb_customer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.entity.GeoEntity;

public interface GeoOperation {
  @GetMapping(value = "/geo/{id}")
  ApiResp<GeoEntity> getGeo(@PathVariable Long id);

  @GetMapping(value = "/geoes")
  ApiResp<List<GeoEntity>> getGeoes();

  @PostMapping(value = "/geo")
  ApiResp<GeoEntity> createGeo(@RequestBody GeoEntity geoEntity);
}
