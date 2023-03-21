package com.tekion.cricketgamespring.controller;

import com.tekion.cricketgamespring.model.Tournament;
import com.tekion.cricketgamespring.service.impl.ScorecardServiceImpl;
import com.tekion.cricketgamespring.service.impl.TeamServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.tekion.cricketgamespring.model.Team;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cricket")
public class MatchController {
    private ScorecardServiceImpl scorecardServiceimpl;
    private TeamServiceImpl teamServiceimpl;
    private Logger logger = LoggerFactory.getLogger(MatchController.class);

    public MatchController(ScorecardServiceImpl scorecardServiceimpl, TeamServiceImpl teamServiceimpl) {
        this.scorecardServiceimpl = scorecardServiceimpl;
        this.teamServiceimpl = teamServiceimpl;
    }

    @PostMapping("startMatch")
    public Tournament startMatch(@RequestBody List<Team> t) {
//        logger.info(scorecardServiceimpl.selectTwoTeams(t).toString());
        Tournament tournament = scorecardServiceimpl.selectTwoTeams(t);
        logger.info("match over");
        return tournament;
    }
}
