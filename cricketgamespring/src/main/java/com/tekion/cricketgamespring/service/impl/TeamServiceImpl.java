package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.dao.TeamRepo;
import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepo teamRepo;
    private PlayerServiceImpl playerServiceImpl;
    private Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    public TeamServiceImpl(TeamRepo teamRepo, PlayerServiceImpl playerServiceImpl) {
        this.teamRepo = teamRepo;
        this.playerServiceImpl = playerServiceImpl;
    }

    @Override
    public Team enterTeam(Team team) {
        teamRepo.save(team);
//        playerServiceImpl.enterPlayer(team);
        return team;
    }

    @Override
    public List<Team> getTeam() {
        return teamRepo.findAll();
    }

    @Override
    public void deleteTeam(int teamId) {
        playerServiceImpl.deletePlayer(teamId);
        teamRepo.deleteById(teamId);
    }

    @Override
    public void checkIfTeamExist(String name) throws Exception {
        if(teamRepo.findByTeam_name(name) != null){
            throw new Exception("Team Exists");
        }
    }

    @Override
    public List<String> playing(Team team1, Team team2) {
        logger.info("{} and {}", team1.getTeamId(), team2.getTeamId());
        List<String> twoTeam = teamRepo.getTwoTeamById(team1.getTeamId(), team2.getTeamId());
        logger.info(twoTeam.toString());
        return twoTeam;
    }

    @Override
    public void updateStatus(String status, int id) {
        teamRepo.updateStatusById(status, id);
    }

    @Override
    public Team updateTeam(Team team) {
        Team t = teamRepo.save(team);
//        playerServiceImpl.updatePlayer(t.getTeam_name(), t.getTeamId(), playerServiceImpl.getPlayerId(t.getTeamId()));
        return t;
    }

}
