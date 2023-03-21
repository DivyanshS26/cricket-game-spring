package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.dao.PlayerRepo;
import com.tekion.cricketgamespring.model.Player;
import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private PlayerRepo playerRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public List<Player> enterPlayer(Team team) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (i < 7) {
                Player pl = new Player();
                pl.setName(team.getTeam_name() + (i + 1));
                pl.setTeam(team);
                pl.setType("Batsman");
                pl.setScore("DNB");
                playerRepo.save(pl);
                logger.info(Integer.toString(pl.getPlayerId()));
                playerList.add(pl);
            } else {
                Player pl = new Player();
                pl.setName(team.getTeam_name() + (i + 1));
                pl.setTeam(team);
                pl.setType("Bowler");
                pl.setScore("DNB");
                playerRepo.save(pl);
                playerList.add(pl);
            }
        }
        return playerList;
    }

    @Override
    public void deletePlayer(int teamId) {
        playerRepo.deleteAllByTeamId(teamId);
    }

    @Override
    public void updatePlayer(String name, int teamId, List<Integer> id) {
        for (int i = 0; i < 11; i++) {
            if (i <= 7) {
                playerRepo.updateNameByTeamId(teamId, name + (i + 1), id.get(i));
            } else {
                playerRepo.updateNameByTeamId(teamId, name + (i + 1), id.get(i));
            }
        }
    }

    @Override
    public List<Integer> getPlayerId(int teamId) {
        return playerRepo.getPlayerIdByTeamId(teamId);
    }

    @Override
    public void addScore(String score, int id) {
//        playerRepo.
    }

    @Override
    public List<Player> getPlayerByTeamId(int teamId) {
        return playerRepo.getPlayersByTeamId(teamId);
    }


}
