package com.study.server.ex2.repository;

import com.study.server.ex2.domain.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameResult, Integer> {
}
