package com.tekion.cricketgamespring.controller;

import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.service.impl.TeamServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cricket")
public class TeamController {
    private Logger logger = LoggerFactory.getLogger(TeamController.class);
    private TeamServiceImpl teamServiceImpl;
    @Autowired
    public TeamController(TeamServiceImpl teamServiceImpl) {
        this.teamServiceImpl = teamServiceImpl;
    }
    @GetMapping
    public List<Team> showTeam(){
        logger.info("getting");
        return teamServiceImpl.getTeam();
    }
    @PostMapping
//    @ExceptionHandler({ Exception.class })
    public Team enterTeam(@RequestBody Team t){
        logger.info("post called {}" , t.toString());
        try {
            teamServiceImpl.checkIfTeamExist(t.getTeam_name());
            Team team = teamServiceImpl.enterTeam(t);
            return team;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team Already Exists", e);
        }
    }
    @DeleteMapping("{teamId}")
    public void deleteTeam(@PathVariable int teamId){
        logger.info("delete");
        teamServiceImpl.deleteTeam(teamId);
    }
    @PutMapping
    public Team updateTeam(@RequestBody Team team){
        logger.info("update");
        return teamServiceImpl.updateTeam(team);
    }
//    @GetMapping("/play")
//    public List<String> playTeam(){
//
//    }
}
