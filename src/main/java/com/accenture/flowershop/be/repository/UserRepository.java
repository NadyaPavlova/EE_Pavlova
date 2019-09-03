package com.accenture.flowershop.be.repository;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByLogin(String login);
    User getUserById(Long id);
}
