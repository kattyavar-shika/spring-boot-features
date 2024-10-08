package com.kattyavar.shika.jpademo.repository;

import com.kattyavar.shika.jpademo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}