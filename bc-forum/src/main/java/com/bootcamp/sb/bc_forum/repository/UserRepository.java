package com.bootcamp.sb.bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.bc_forum.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
}
