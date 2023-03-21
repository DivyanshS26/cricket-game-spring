package com.tekion.cricketgamespring.service;

import com.tekion.cricketgamespring.model.Team;

import java.util.List;

public interface TeamService {
    Team enterTeam(Team team);
    List<Team> getTeam();
    void deleteTeam(int teamId);
    Team updateTeam(Team team);
    void updateStatus(String status, int id);
    List<String> playing(Team team1, Team team2);

    void checkIfTeamExist(String name) throws Exception;

}
