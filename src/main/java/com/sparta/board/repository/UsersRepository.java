package com.sparta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.board.entity.Members;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByUsername(String username);
}
