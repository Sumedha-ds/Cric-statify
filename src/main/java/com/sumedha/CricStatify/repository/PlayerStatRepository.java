package com.sumedha.CricStatify.repository;

import com.sumedha.CricStatify.model.PlayerStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerStatRepository extends JpaRepository<PlayerStat, Long> {
    Optional<PlayerStat> findByName(String name);
}
