package com.tekion.cricketgamespring.dao;

import com.tekion.cricketgamespring.model.Scorecard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScorecardRepo extends JpaRepository<Scorecard, Integer> {
}
