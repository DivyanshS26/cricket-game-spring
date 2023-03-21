package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.dao.ScoreCardListRepo;
import com.tekion.cricketgamespring.model.ScoreCardList;
import com.tekion.cricketgamespring.service.ScoreCardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardListServiceImpl implements ScoreCardListService {
    private ScoreCardListRepo scoreCardListRepo;

    @Autowired
    public ScoreCardListServiceImpl(ScoreCardListRepo scoreCardListRepo) {
        this.scoreCardListRepo = scoreCardListRepo;
    }

    @Override
    public void addScoreCardList(ScoreCardList scl) {
        scoreCardListRepo.save(scl);
    }
}
