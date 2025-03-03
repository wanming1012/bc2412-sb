package com.bootcamp.sb.bc_forum.entity.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;
import com.bootcamp.sb.bc_forum.dto.PostDTO;
import com.bootcamp.sb.bc_forum.dto.UserCommentsDTO;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.dto.UserPostsCommentsDTO;
import com.bootcamp.sb.bc_forum.entity.AddressEntity;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;
import com.bootcamp.sb.bc_forum.entity.CompanyEntity;
import com.bootcamp.sb.bc_forum.entity.GeoEntity;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import com.bootcamp.sb.bc_forum.entity.UserEntity;
import com.bootcamp.sb.bc_forum.model.dto.CommentDto;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;

@Component
public class EntityMapper {
  public UserEntity map(UserDto userDto) {
    return UserEntity.builder().name(userDto.getName())
        .username(userDto.getUsername())
        .email(userDto.getEmail())
        .phone(userDto.getPhone())
        .website(userDto.getWebsite()).build();
  }

  public AddressEntity map(UserDto.Address addressDto) {
    return AddressEntity.builder().street(addressDto.getStreet())
        .suite(addressDto.getSuite())
        .city(addressDto.getCity())
        .zipcode(addressDto.getZipcode()).build();
  }

  public GeoEntity map(UserDto.Address.Geo geoDto) {
    return GeoEntity.builder().latitude(geoDto.getLat())
        .longitude(geoDto.getLng()).build();
  }

  public CompanyEntity map(UserDto.Company companyDto) {
    return CompanyEntity.builder().name(companyDto.getName())
        .catchPhrase(companyDto.getCatchPhrase())
        .bs(companyDto.getBs()).build();
  }

  public PostEntity map(PostDto postDto) {
    return PostEntity.builder().title(postDto.getTitle())
        .body(postDto.getBody()).build();
  }

  public CommentEntity map(CommentDto commentDto) {
    return CommentEntity.builder().name(commentDto.getName())
        .email(commentDto.getEmail())
        .body(commentDto.getBody()).build();
  }

  public UserPostsCommentsDTO map(UserEntity user, List<PostEntity> posts, 
      List<CommentEntity> comments) {
    UserPostsCommentsDTO.Address address = null;
    UserPostsCommentsDTO.Address.Geo geo = null;
    UserPostsCommentsDTO.Company company = null;
    if (user.getAddressEntity() != null) {
      if (user.getAddressEntity().getGeoEntity() != null) {
        geo = UserPostsCommentsDTO.Address.Geo.builder()
            .lat(user.getAddressEntity().getGeoEntity().getLatitude())
            .lng(user.getAddressEntity().getGeoEntity().getLongitude())
            .build();
      }
      address = UserPostsCommentsDTO.Address.builder()
          .street(user.getAddressEntity().getStreet())
          .suite(user.getAddressEntity().getSuite())
          .city(user.getAddressEntity().getCity())
          .zipcode(user.getAddressEntity().getZipcode())
          .geo(geo)
          .build();
    }
    if (user.getCompanyEntity() != null) {
      company = UserPostsCommentsDTO.Company.builder()
          .name(user.getCompanyEntity().getName())
          .catchPhrase(user.getCompanyEntity().getCatchPhrase())
          .bs(user.getCompanyEntity().getBs())
          .build();
    }

    return UserPostsCommentsDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .username(user.getUsername())
        .email(user.getEmail())
        .address(address)
        .phone(user.getPhone())
        .website(user.getWebsite())
        .company(company)
        .posts(posts.stream()
            .filter(p -> p.getUserEntity().getId().equals(user.getId()))
            .map(p -> UserPostsCommentsDTO.Post.builder()
                .id(p.getId())
                .title(p.getTitle())
                .body(p.getBody())
                .comments(comments.stream()
                    .filter(c -> c.getPostEntity().getId().equals(p.getId()))
                    .map(c -> UserPostsCommentsDTO.Post.Comment.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .email(c.getEmail())
                        .body(c.getBody())
                        .build())
                    .collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList()))
        .build();
  }

  public UserDTO map(UserEntity user) {
    UserDTO.Address address = null;
    UserDTO.Address.Geo geo = null;
    UserDTO.Company company = null;
    if (user.getAddressEntity() != null) {
      if (user.getAddressEntity().getGeoEntity() != null) {
        geo = UserDTO.Address.Geo.builder()
            .lat(user.getAddressEntity().getGeoEntity().getLatitude())
            .lng(user.getAddressEntity().getGeoEntity().getLongitude())
            .build();
      }
      address = UserDTO.Address.builder()
          .street(user.getAddressEntity().getStreet())
          .suite(user.getAddressEntity().getSuite())
          .city(user.getAddressEntity().getCity())
          .zipcode(user.getAddressEntity().getZipcode())
          .geo(geo)
          .build();
    }
    if (user.getCompanyEntity() != null) {
      company = UserDTO.Company.builder()
          .name(user.getCompanyEntity().getName())
          .catchPhrase(user.getCompanyEntity()
          .getCatchPhrase())
          .bs(user.getCompanyEntity().getBs())
          .build();
    }

    return UserDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .username(user.getUsername())
        .email(user.getEmail())
        .address(address)
        .phone(user.getPhone())
        .website(user.getWebsite())
        .company(company)
        .build();
  }

  public PostDTO map(PostEntity post) {
    return PostDTO.builder()
        .id(post.getId())
        .title(post.getTitle())
        .body(post.getBody())
        .username(post.getUserEntity().getUsername())
        .build();
  }

  public CommentDTO map(CommentEntity comment) {
    return CommentDTO.builder()
        .id(comment.getId())
        .name(comment.getName())
        .email(comment.getEmail())
        .body(comment.getBody())
        .postId(comment.getPostEntity().getId())
        .build();
  }

  public UserCommentsDTO map(Long id, String username, List<PostEntity> posts, List<CommentEntity> comments) {
    List<UserCommentsDTO.Comment> userComments = new ArrayList<>();
    for (PostEntity post : posts) {
      userComments.addAll(comments.stream()
          .filter(c -> c.getPostEntity().getId().equals(post.getId()))
          .map(c -> UserCommentsDTO.Comment.builder()
              .name(c.getName())
              .email(c.getEmail())
              .body(c.getBody())
              .build())
          .collect(Collectors.toList()));
    }
    
    return UserCommentsDTO.builder()
        .id(id)
        .username(username)
        .comments(userComments)
        .build();
  }
}
