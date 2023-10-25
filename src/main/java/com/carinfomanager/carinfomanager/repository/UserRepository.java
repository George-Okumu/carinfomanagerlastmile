package com.carinfomanager.carinfomanager.repository;

import com.carinfomanager.carinfomanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
