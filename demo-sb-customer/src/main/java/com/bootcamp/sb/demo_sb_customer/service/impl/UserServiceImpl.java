package com.bootcamp.sb.demo_sb_customer.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sb.demo_sb_customer.model.dto.UserDto;
import com.bootcamp.sb.demo_sb_customer.repository.UserRepository;
import com.bootcamp.sb.demo_sb_customer.service.AddressService;
import com.bootcamp.sb.demo_sb_customer.service.CompanyService;
import com.bootcamp.sb.demo_sb_customer.service.GeoService;
import com.bootcamp.sb.demo_sb_customer.service.UserService;
import com.bootcamp.sb.demo_sb_customer.codewave.BusinessException;
import com.bootcamp.sb.demo_sb_customer.codewave.SysCode;
import com.bootcamp.sb.demo_sb_customer.entity.AddressEntity;
import com.bootcamp.sb.demo_sb_customer.entity.CompanyEntity;
import com.bootcamp.sb.demo_sb_customer.entity.GeoEntity;
import com.bootcamp.sb.demo_sb_customer.entity.UserEntity;
import com.bootcamp.sb.demo_sb_customer.entity.mapper.EntityMapper;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CompanyService companyService;

  @Autowired
  private AddressService addressService;

  @Autowired
  private GeoService geoService;

  @Autowired
  private EntityMapper entityMapper;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.users}")
  private String userEndpoint;

  @Override
  public List<UserDto> importUsers() {
    String uri = UriComponentsBuilder.newInstance()
      .scheme("https")
      .host(domain)
      .path(userEndpoint)
      .build()
      .toUriString();

    System.out.println(uri);

    List<UserDto> userDtos = Arrays.asList(this.restTemplate.getForObject(
        uri, UserDto[].class));
    userDtos.forEach(e -> {
      GeoEntity geoEntity = this.entityMapper.map(e.getAddress().getGeo());
      this.geoService.createGeo(geoEntity);

      AddressEntity addressEntity = this.entityMapper.map(e.getAddress());
      addressEntity.setGeoEntity(geoEntity);
      this.addressService.createAddress(geoEntity.getId(), addressEntity);

      CompanyEntity companyEntity = this.entityMapper.map(e.getCompany());
      this.companyService.createCompany(companyEntity);

      UserEntity userEntity = this.entityMapper.map(e);
      createUser(addressEntity.getId(), companyEntity.getId(), userEntity);
    });

    return userDtos;
  }

  @Override
  public UserEntity getUser(Long id) {
    return this.userRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
  }

  @Override
  public List<UserEntity> getUsers() {
    return this.userRepository.findAll();
  }

  @Override
  public UserEntity createUser(Long addressId, Long companyId, UserEntity userEntity) {
    AddressEntity addressEntity = addressService.getAddress(addressId);
    CompanyEntity companyEntity = companyService.getCompany(companyId);
    userEntity.setAddressEntity(addressEntity);
    userEntity.setCompanyEntity(companyEntity);
    return this.userRepository.save(userEntity);
  }
}
