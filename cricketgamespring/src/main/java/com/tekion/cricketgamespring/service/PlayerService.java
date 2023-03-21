package com.tekion.cricketgamespring.service;

import com.tekion.cricketgamespring.model.Player;
import com.tekion.cricketgamespring.model.Scorecard;
import com.tekion.cricketgamespring.model.Team;

import java.util.List;

public interface PlayerService {
    List<Player> enterPlayer(Team team);
    void deletePlayer(int teamId);
    void updatePlayer(String name, int teamId, List<Integer> id);
    List<Integer> getPlayerId(int teamId);
    List<Player> getPlayerByTeamId(int teamId);
    void addScore(String score, int id);

}
