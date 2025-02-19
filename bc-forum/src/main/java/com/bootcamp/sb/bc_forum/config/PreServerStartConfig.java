package com.bootcamp.sb.bc_forum.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sb.bc_forum.codewave.BusinessException;
import com.bootcamp.sb.bc_forum.codewave.SysCode;
import com.bootcamp.sb.bc_forum.entity.AddressEntity;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;
import com.bootcamp.sb.bc_forum.entity.CompanyEntity;
import com.bootcamp.sb.bc_forum.entity.GeoEntity;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import com.bootcamp.sb.bc_forum.entity.UserEntity;
import com.bootcamp.sb.bc_forum.entity.mapper.EntityMapper;
import com.bootcamp.sb.bc_forum.model.dto.CommentDto;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;
import com.bootcamp.sb.bc_forum.repository.AddressRepository;
import com.bootcamp.sb.bc_forum.repository.CommentRepository;
import com.bootcamp.sb.bc_forum.repository.CompanyRepository;
import com.bootcamp.sb.bc_forum.repository.GeoRepository;
import com.bootcamp.sb.bc_forum.repository.PostRepository;
import com.bootcamp.sb.bc_forum.repository.UserRepository;

@Component
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private GeoRepository geoRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private EntityMapper entityMapper;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.users}")
  private String userEndpoint;

  @Value("${api.jsonplaceholder.endpoints.posts}")
  private String postEndpoint;

  @Value("${api.jsonplaceholder.endpoints.comments}")
  private String commentEndpoint;

  @Override
  public void run(String... args) throws Exception {
    this.commentRepository.deleteAll();
    this.postRepository.deleteAll();
    this.userRepository.deleteAll();
    this.companyRepository.deleteAll();
    this.addressRepository.deleteAll();
    this.geoRepository.deleteAll();

    importUsers();
    importPosts();
    importComments();
  }

  public void importUsers() {
    String uri = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(userEndpoint).build().toUriString();

    List<UserDto> userDtos =
        Arrays.asList(this.restTemplate.getForObject(uri, UserDto[].class));

    userDtos.forEach(e -> {
      GeoEntity geoEntity = this.entityMapper.map(e.getAddress().getGeo());
      this.geoRepository.save(geoEntity);

      AddressEntity addressEntity = this.entityMapper.map(e.getAddress());
      addressEntity.setGeoEntity(geoEntity);
      this.addressRepository.save(addressEntity);

      CompanyEntity companyEntity = this.entityMapper.map(e.getCompany());
      this.companyRepository.save(companyEntity);

      UserEntity userEntity = this.entityMapper.map(e);
      userEntity.setAddressEntity(addressEntity);
      userEntity.setCompanyEntity(companyEntity);
      this.userRepository.save(userEntity);
    });

    System.out.println("size" + userDtos.size());
  }

  public void importPosts() {
    String uri = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(postEndpoint).build().toUriString();

    List<PostDto> postDtos =
        Arrays.asList(this.restTemplate.getForObject(uri, PostDto[].class));

    postDtos.forEach(e -> {
      PostEntity postEntity = entityMapper.map(e);
      UserEntity userEntity = this.userRepository.findById(e.getUserId())
          .orElseThrow(() -> BusinessException.of(SysCode.USER_ID_NOT_FOUND));
      postEntity.setUserEntity(userEntity);
      this.postRepository.save(postEntity);
    });
  }

  public void importComments() {
    String uri = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(commentEndpoint).build().toUriString();

    List<CommentDto> commentDtos =
        Arrays.asList(this.restTemplate.getForObject(uri, CommentDto[].class));

    commentDtos.forEach(e -> {
      CommentEntity commentEntity = entityMapper.map(e);
      PostEntity postEntity = this.postRepository.findById(e.getPostId())
          .orElseThrow(() -> BusinessException.of(SysCode.POST_ID_NOT_FOUND));

      commentEntity.setPostEntity(postEntity);

      this.commentRepository.save(commentEntity);
    });
  }
}
