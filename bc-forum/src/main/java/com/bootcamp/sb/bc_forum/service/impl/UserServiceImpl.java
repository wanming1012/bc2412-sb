package com.bootcamp.sb.bc_forum.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sb.bc_forum.codewave.BusinessException;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.dto.UserCommentsDTO;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.dto.UserPostsCommentsDTO;
import com.bootcamp.sb.bc_forum.entity.AddressEntity;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;
import com.bootcamp.sb.bc_forum.entity.CompanyEntity;
import com.bootcamp.sb.bc_forum.entity.GeoEntity;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import com.bootcamp.sb.bc_forum.entity.UserEntity;
import com.bootcamp.sb.bc_forum.entity.mapper.EntityMapper;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;
import com.bootcamp.sb.bc_forum.repository.AddressRepository;
import com.bootcamp.sb.bc_forum.repository.CommentRepository;
import com.bootcamp.sb.bc_forum.repository.CompanyRepository;
import com.bootcamp.sb.bc_forum.repository.GeoRepository;
import com.bootcamp.sb.bc_forum.repository.PostRepository;
import com.bootcamp.sb.bc_forum.repository.UserRepository;
import com.bootcamp.sb.bc_forum.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private GeoRepository geoRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

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

    return userEntities.stream()
        .map(e -> this.entityMapper.map(e))
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO replaceUser(UserDto userDto) {
    UserEntity oldUserEntity = this.userRepository.findById(userDto.getId())
        .orElseThrow(() -> BusinessException.of(SysCode.USER_ID_NOT_FOUND));

    GeoEntity geoEntity = this.entityMapper.map(userDto.getAddress().getGeo());
    geoEntity.setId(oldUserEntity.getAddressEntity().getGeoEntity().getId());
    this.geoRepository.save(geoEntity);

    AddressEntity addressEntity = this.entityMapper.map(userDto.getAddress());
    addressEntity.setId(oldUserEntity.getAddressEntity().getId());
    addressEntity.setGeoEntity(geoEntity);
    this.addressRepository.save(addressEntity);

    CompanyEntity companyEntity = this.entityMapper.map(userDto.getCompany());
    companyEntity.setId(oldUserEntity.getCompanyEntity().getId());
    this.companyRepository.save(companyEntity);

    UserEntity userEntity = this.entityMapper.map(userDto);
    userEntity.setId(userDto.getId());
    userEntity.setAddressEntity(addressEntity);
    userEntity.setCompanyEntity(companyEntity);

    return this.entityMapper.map(this.userRepository.save(userEntity));
  }

  public List<UserPostsCommentsDTO> getAllPostsAndComments() {
    List<UserEntity> userEntities = this.userRepository.findAll();
    List<PostEntity> postEntities = this.postRepository.findAll();
    List<CommentEntity> commentEntities = this.commentRepository.findAll();

    return userEntities.stream()
        .map(e -> this.entityMapper.map(e, postEntities, commentEntities))
        .collect(Collectors.toList());
  }

  public UserCommentsDTO getAllUserComments(Long id) {
    UserEntity userEntity = this.userRepository.findById(id)
        .orElseThrow(() -> BusinessException.of(SysCode.USER_ID_NOT_FOUND));
    
    List<PostEntity> postEntities = this.postRepository.findByUserEntity(userEntity);
    List<CommentEntity> commentEntities = this.commentRepository.findAll();

    return this.entityMapper.map(id, userEntity.getUsername(), postEntities, commentEntities);
  }
}
