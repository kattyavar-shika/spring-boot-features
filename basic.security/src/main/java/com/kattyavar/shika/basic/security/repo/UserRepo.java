package com.kattyavar.shika.basic.security.repo;

import com.kattyavar.shika.basic.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
  User findByUsername(String userName);
}
