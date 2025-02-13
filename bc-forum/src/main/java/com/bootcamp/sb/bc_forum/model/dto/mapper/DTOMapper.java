package com.bootcamp.sb.bc_forum.model.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.bc_forum.dto.CommentDTO;
import com.bootcamp.sb.bc_forum.dto.UserDTO;
import com.bootcamp.sb.bc_forum.model.dto.CommentDto;
import com.bootcamp.sb.bc_forum.model.dto.PostDto;
import com.bootcamp.sb.bc_forum.model.dto.UserDto;

@Component
public class DTOMapper {
    public UserDTO mapUser(UserDto user, List<PostDto> posts,
            List<CommentDto> comments) {
        List<PostDto> userPosts =
                posts.stream().filter(e -> e.getUserId() == user.getId())
                        .collect(Collectors.toList());

        ArrayList<UserDTO.Post> mapPosts = new ArrayList<>();
        for (PostDto post : userPosts) {
            List<CommentDto> postComments =
                    comments.stream().filter(e -> e.getPostId() == post.getId())
                            .collect(Collectors.toList());
            ArrayList<UserDTO.Post.Comment> mapComments = new ArrayList<>();
            for (CommentDto comment : postComments) {
                mapComments.add(UserDTO.Post.Comment.builder()
                        .id(comment.getId()).name(comment.getName())
                        .email(comment.getEmail()).body(comment.getBody())
                        .build());
            }

            mapPosts.add(UserDTO.Post.builder().id(post.getId())
                    .title(post.getTitle()).body(post.getBody())
                    .comments(mapComments).build());
        }

        return UserDTO.builder().id(user.getId()).name(user.getName())
                .username(user.getUsername()).email(user.getEmail())
                .address(UserDTO.Address.builder()
                        .street(user.getAddress().getStreet())
                        .suite(user.getAddress().getSuite())
                        .city(user.getAddress().getCity())
                        .zipcode(user.getAddress().getZipcode())
                        .geo(UserDTO.Address.Geo.builder()
                                .lat(user.getAddress().getGeo().getLat())
                                .lng(user.getAddress().getGeo().getLng())
                                .build())
                        .build())
                .phone(user.getPhone()).website(user.getWebsite())
                .company(UserDTO.Company.builder()
                        .name(user.getCompany().getName())
                        .catchPhrase(user.getCompany().getCatchPhrase())
                        .bs(user.getCompany().getBs()).build())
                .posts(mapPosts).build();
    }

    public CommentDTO mapComment(UserDto user, List<PostDto> posts,
            List<CommentDto> comments) {
        List<PostDto> userPosts =
                posts.stream().filter(e -> e.getUserId() == user.getId())
                        .collect(Collectors.toList());

        ArrayList<CommentDTO.Comment> mapComments = new ArrayList<>();
        for (PostDto post : userPosts) {
            List<CommentDto> postComments =
                    comments.stream().filter(e -> e.getPostId() == post.getId())
                            .collect(Collectors.toList());
            for (CommentDto comment : postComments) {
                mapComments.add(CommentDTO.Comment.builder()
                        .name(comment.getName()).email(comment.getEmail())
                        .body(comment.getBody()).build());
            }
        }

        return CommentDTO.builder().id(user.getId())
                .username(user.getUsername()).comments(mapComments).build();
    }
}
