package com.yofujitsu.youtubeclone.dao.repositories;

import com.yofujitsu.youtubeclone.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
