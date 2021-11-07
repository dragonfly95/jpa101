package com.example.jpa101.board.repository;

import com.example.jpa101.board.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Override
//    @Query("select distinct board from BoardEntity board join fetch board.comments ")
    List<BoardEntity> findAll();
}
