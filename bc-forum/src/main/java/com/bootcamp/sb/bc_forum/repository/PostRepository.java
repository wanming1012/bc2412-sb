package com.bootcamp.sb.bc_forum.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import com.bootcamp.sb.bc_forum.entity.UserEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  List<PostEntity> findByUserEntity(UserEntity userEntity);
} 