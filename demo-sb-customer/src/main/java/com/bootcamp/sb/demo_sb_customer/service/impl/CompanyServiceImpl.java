package com.bootcamp.sb.demo_sb_customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.demo_sb_customer.codewave.BusinessException;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.entity.CompanyEntity;
import com.bootcamp.sb.demo_sb_customer.repository.CompanyRepository;
import com.bootcamp.sb.demo_sb_customer.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
  @Autowired
  private CompanyRepository companyRepository;

  @Override
  public CompanyEntity getCompany(Long id) {
    return this.companyRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
  }

  @Override
  public List<CompanyEntity> getCompanies() {
    return this.companyRepository.findAll();
  }

  @Override
  public CompanyEntity createCompany(CompanyEntity companyEntity) {
    return this.companyRepository.save(companyEntity);
  }
}
