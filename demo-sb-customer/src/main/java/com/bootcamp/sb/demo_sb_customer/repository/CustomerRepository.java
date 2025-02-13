package com.bootcamp.sb.demo_sb_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;

// ! Hibernate generates the implementation class automatically
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
  
}
