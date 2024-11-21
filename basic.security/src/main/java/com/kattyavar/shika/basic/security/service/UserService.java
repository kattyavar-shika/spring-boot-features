package com.kattyavar.shika.basic.security.service;

import com.kattyavar.shika.basic.security.model.User;
import com.kattyavar.shika.basic.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

  public User registerUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepo.save(user);
  }
}
