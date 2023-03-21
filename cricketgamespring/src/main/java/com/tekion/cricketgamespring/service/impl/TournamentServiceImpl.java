package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.dao.TournamentRepo;
import com.tekion.cricketgamespring.model.Tournament;
import com.tekion.cricketgamespring.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements TournamentService {
    private TournamentRepo tournamentRepo;

    @Autowired
    public TournamentServiceImpl(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public void addTournament(Tournament tournament) {
        tournamentRepo.save(tournament);
    }
}
