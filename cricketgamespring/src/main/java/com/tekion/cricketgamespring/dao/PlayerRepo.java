package com.tekion.cricketgamespring.dao;

import com.tekion.cricketgamespring.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    @Modifying
    @Transactional
    @Query("delete from Player p where p.team.id=:id")
    void deleteAllByTeamId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("Update Player p SET p.name = :name where p.id = :id and p.team.id=:teamid")
    void updateNameByTeamId(@Param("teamid") int teamid, @Param("name") String name, @Param("id") int id);

    @Query("select p.id from Player p where p.team.id= :id")
    List<Integer> getPlayerIdByTeamId(@Param("id") int id);

    @Query("select p from Player p where p.team.id= :id")
    List<Player> getPlayersByTeamId(@Param("id") int id);
    @Modifying
    @Transactional
    @Query("update Player p set p.score = :score where p.id = :id")
    void setScoreById(@Param("score") String score, @Param("id") int id);


}
