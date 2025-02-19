package com.bootcamp.sb.bc_forum.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.bc_forum.codewave.BusinessException;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.entity.AddressEntity;
import com.bootcamp.sb.bc_forum.entity.CompanyEntity;
import com.bootcamp.sb.bc_forum.entity.GeoEntity;
import com.bootcamp.sb.bc_forum.entity.UserEntity;
import com.bootcamp.sb.bc_forum.entity.mapper.EntityMapper;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;
import com.bootcamp.sb.bc_forum.repository.AddressRepository;
import com.bootcamp.sb.bc_forum.repository.CompanyRepository;
import com.bootcamp.sb.bc_forum.repository.GeoRepository;
import com.bootcamp.sb.bc_forum.repository.UserRepository;
import com.bootcamp.sb.bc_forum.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private GeoRepository geoRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private EntityMapper entityMapper;

  @Override
  public UserDTO getUser(Long id) {
    UserEntity userEntity = this.userRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.USER_ID_NOT_FOUND));
    return this.entityMapper.map(userEntity);
  }

  @Override
  public List<UserDTO> getUsers() {
    List<UserEntity> userEntities = this.userRepository.findAll();
    return userEntities.stream().map(e -> this.entityMapper.map(e))
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO replaceUser(UserDto userDto) {
    if (!this.userRepository.existsById(userDto.getId()))
      throw BusinessException.of(SysCode.USER_ID_NOT_FOUND);

    GeoEntity geoEntity = this.entityMapper.map(userDto.getAddress().getGeo());
    this.geoRepository.save(geoEntity);

    AddressEntity addressEntity = this.entityMapper.map(userDto.getAddress());
    addressEntity.setGeoEntity(geoEntity);
    this.addressRepository.save(addressEntity);

    CompanyEntity companyEntity = this.entityMapper.map(userDto.getCompany());
    this.companyRepository.save(companyEntity);

    UserEntity userEntity = this.entityMapper.map(userDto);
    userEntity.setId(userDto.getId());
    userEntity.setAddressEntity(addressEntity);
    userEntity.setCompanyEntity(companyEntity);

    return this.entityMapper
        .map(this.userRepository.save(userEntity));
  }
}
