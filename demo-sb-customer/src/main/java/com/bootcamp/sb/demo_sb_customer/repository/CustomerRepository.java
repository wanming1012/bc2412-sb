package com.bootcamp.sb.demo_sb_customer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sb.demo_sb_customer.entity.CustomerEntity;

// ! Hibernate generates the implementation class automatically
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
  

  // select * from Customers where name = 'John'
  Optional<CustomerEntity> findByName(String name);
}
