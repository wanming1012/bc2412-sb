package com.bootcamp.sb.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.controller.CompanyOperation;
import com.bootcamp.sb.demo_sb_customer.entity.CompanyEntity;
import com.bootcamp.sb.demo_sb_customer.service.CompanyService;

@RestController
@RequestMapping(value = "/api/v1")
public class CompanyController implements CompanyOperation {
  @Autowired
  private CompanyService companyService;

  @Override
  public ApiResp<CompanyEntity> getCompany(Long id) {
    return ApiResp.<CompanyEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.companyService.getCompany(id))
      .build();
  }

  @Override
  public ApiResp<List<CompanyEntity>> getCompanies() {
    return ApiResp.<List<CompanyEntity>>builder()
      .sysCode(SysCode.OK)
      .data(this.companyService.getCompanies())
      .build();
  }

  @Override
  public ApiResp<CompanyEntity> createCompany(CompanyEntity companyEntity) {
    return ApiResp.<CompanyEntity>builder()
      .sysCode(SysCode.OK)
      .data(this.companyService.createCompany(companyEntity))
      .build();
  }
}
