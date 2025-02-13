package com.bootcamp.sb.demo_sb_customer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sb.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.sb.demo_sb_customer.entity.CompanyEntity;

public interface CompanyOperation {
  @GetMapping(value = "/company/{id}")
  ApiResp<CompanyEntity> getCompany(@RequestParam Long id);

  @GetMapping(value = "/companies")
  ApiResp<List<CompanyEntity>> getCompanies();

  @PostMapping(value = "/company")
  ApiResp<CompanyEntity> createCompany(@RequestBody CompanyEntity companyEntity);
}
