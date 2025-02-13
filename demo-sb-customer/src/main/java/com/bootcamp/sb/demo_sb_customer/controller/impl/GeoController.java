package com.bootcamp.sb.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.controller.GeoOperation;
import com.bootcamp.sb.demo_sb_customer.entity.GeoEntity;
import com.bootcamp.sb.demo_sb_customer.service.GeoService;

@RestController
@RequestMapping(value = "/api/v1")
public class GeoController implements GeoOperation {
  @Autowired
  private GeoService geoService;

  @Override
  public ApiResp<GeoEntity> getGeo(Long id) {
    return ApiResp.<GeoEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.geoService.getGeo(id))
      .build();
  }

  @Override
  public ApiResp<List<GeoEntity>> getGeoes() {
    return ApiResp.<List<GeoEntity>>builder()
      .sysCode(SysCode.OK)
      .data(this.geoService.getGeoes())
      .build();
  }

  @Override
  public ApiResp<GeoEntity> createGeo(GeoEntity geoEntity) {
    return ApiResp.<GeoEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.geoService.createGeo(geoEntity))
      .build();
  }
}
