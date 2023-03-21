package com.tekion.cricketgamespring.dao;

import com.tekion.cricketgamespring.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Integer> {
    @Modifying
    @Transactional
    @Query("update Team t set t.status = :status where t.id = :id")
    void updateStatusById(@Param("status") String status, @Param("id") int id);
    @Modifying
    @Transactional
    @Query("select t.team_name from Team t where t.id = :id1 or t.id = :id2")
    List<String> getTwoTeamById(@Param("id1") int id1, @Param("id2") int id2);
    @Query("select t.team_name from Team t where t.team_name = :name")
    String findByTeam_name(String name);
}
