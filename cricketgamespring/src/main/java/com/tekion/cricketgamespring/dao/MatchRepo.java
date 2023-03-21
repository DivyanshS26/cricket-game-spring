package com.tekion.cricketgamespring.dao;

import com.tekion.cricketgamespring.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {
}
