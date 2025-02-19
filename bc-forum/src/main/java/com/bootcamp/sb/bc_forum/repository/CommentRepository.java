package com.bootcamp.sb.bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.bc_forum.entity.CommentEntity;
import com.bootcamp.sb.bc_forum.entity.PostEntity;
import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
  List<CommentEntity> findByPostEntity(PostEntity postEntity);
}
