package com.sparta.mystory.repository;


import com.sparta.mystory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByUsername(String username);
    Optional<User> findByKakaoId(Long kakaoId);
}