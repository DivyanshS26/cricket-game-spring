package com.tekion.cricketgamespring.dao;

import com.tekion.cricketgamespring.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Integer> {
}
