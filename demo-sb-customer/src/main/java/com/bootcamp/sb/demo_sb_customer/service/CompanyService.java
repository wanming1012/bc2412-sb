package com.bootcamp.sb.demo_sb_customer.service;

import java.util.List;
import com.bootcamp.sb.demo_sb_customer.entity.CompanyEntity;

public interface CompanyService {
  CompanyEntity getCompany(Long id);
  List<CompanyEntity> getCompanies();
  CompanyEntity createCompany(CompanyEntity companyEntity);
}