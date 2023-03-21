package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.dao.MatchRepo;
import com.tekion.cricketgamespring.model.Match;
import com.tekion.cricketgamespring.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {
    private Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);
    private MatchRepo matchRepo;

    @Autowired
    public MatchServiceImpl(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    @Override
    public void addMatch(Match match) {
        matchRepo.save(match);
    }
}
