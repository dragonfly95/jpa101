package com.example.jpa101.board.repository;

import com.example.jpa101.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
