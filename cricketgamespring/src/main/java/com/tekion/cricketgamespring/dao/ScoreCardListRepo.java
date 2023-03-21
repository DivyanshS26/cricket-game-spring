package com.tekion.cricketgamespring.dao;

import com.tekion.cricketgamespring.model.ScoreCardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreCardListRepo extends JpaRepository<ScoreCardList, Integer> {
}
