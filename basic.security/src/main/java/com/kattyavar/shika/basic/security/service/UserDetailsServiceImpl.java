package com.kattyavar.shika.basic.security.service;

import com.kattyavar.shika.basic.security.model.User;
import com.kattyavar.shika.basic.security.model.UserPrinciple;
import com.kattyavar.shika.basic.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepo.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User Not found");
    }

    return new UserPrinciple(user);
  }
}
