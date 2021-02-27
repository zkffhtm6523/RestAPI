package com.miniWeb.restAPI.repo;

import com.miniWeb.restAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, Long> {
}
