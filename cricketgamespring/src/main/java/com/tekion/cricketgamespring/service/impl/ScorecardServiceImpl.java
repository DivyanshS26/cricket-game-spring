package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.dao.ScorecardRepo;
import com.tekion.cricketgamespring.model.*;
import com.tekion.cricketgamespring.service.ScorecardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScorecardServiceImpl implements ScorecardService {
    private ScorecardRepo scorecardRepo;
    private PlayerServiceImpl playerServiceimpl;
    private TeamServiceImpl teamServiceImpl;
    private MatchServiceImpl matchServiceImpl;
    private TournamentServiceImpl tournamentServiceImpl;
    private ScoreCardListServiceImpl scoreCardListServiceImpl;
    private Logger logger = LoggerFactory.getLogger(ScorecardServiceImpl.class);
    private List<Match> matchList;

    @Autowired
    public ScorecardServiceImpl(ScorecardRepo scorecardRepo, PlayerServiceImpl playerServiceimpl, TeamServiceImpl teamServiceImpl, MatchServiceImpl matchServiceImpl, TournamentServiceImpl tournamentServiceImpl, ScoreCardListServiceImpl scoreCardListServiceImpl, List<Match> matchList) {
        this.scorecardRepo = scorecardRepo;
        this.playerServiceimpl = playerServiceimpl;
        this.teamServiceImpl = teamServiceImpl;
        this.matchServiceImpl = matchServiceImpl;
        this.tournamentServiceImpl = tournamentServiceImpl;
        this.scoreCardListServiceImpl = scoreCardListServiceImpl;
        this.matchList = matchList;
    }

    @Override
    public Tournament selectTwoTeams(List<Team> team) {
        matchList.clear();
        List<Team> losingTeam = new ArrayList<>();
        while (team.size() > 0) {
            int j = team.size() - 1;
            for (int i = 0; i < team.size() / 2; i++) {

                logger.info("{} teams", teamServiceImpl.playing(team.get(i), team.get(j)).toString());
                Tournament tournament = startMatch(team, team.get(i), team.get(j), losingTeam);
                if (tournament != null) return tournament;
                j--;
            }
            team.removeAll(losingTeam);
        }
        return null;
    }


    public Tournament addTournament(int size, Match match) {
        matchList.add(match);
        logger.info(match.getWinner());
        if (size == 2) {
            Tournament tournament = new Tournament();
            tournament.setWinningTeam(match.getWinner());
            tournament.setMatch(matchList);
            tournamentServiceImpl.addTournament(tournament);
            return tournament;
        }
        return null;
    }

    @Override
    public Tournament startMatch(List<Team> team, Team firstTeam, Team secondTeam, List<Team> losingTeam) {
        List<Scorecard> scorecardList = new ArrayList<>();
//        List<List<Scorecard>> sl = new ArrayList<>();
        List<ScoreCardList> scl = new ArrayList<>();
        int scoreFirstTeam = Batting(firstTeam, Integer.MAX_VALUE, scorecardList, scl, 0);
        logger.info("batting done");
        int scoreSecondTeam = Batting(secondTeam, scoreFirstTeam, scorecardList, scl, scorecardList.size());
        logger.info(scl.toString());
        won(scoreFirstTeam, scoreSecondTeam, firstTeam, secondTeam, losingTeam);
        return addMatch(firstTeam, secondTeam, team.size(), scl);
    }

    @Override
    public void won(int firstTeamScore, int secondTeamScore, Team firstTeam, Team secondTeam, List<Team> losingTeam) {
            logger.info("{}, {} score" ,firstTeamScore, secondTeamScore);
        if (firstTeamScore > secondTeamScore) {
            firstTeam.setStatus("WON");
            secondTeam.setStatus("LOST");
            teamServiceImpl.updateStatus("WON", firstTeam.getTeamId());
            teamServiceImpl.updateStatus("LOST", secondTeam.getTeamId());
            losingTeam.add(secondTeam);
            logger.info("{} , {} status", firstTeam.getStatus(), secondTeam.getStatus());
        } else {
            firstTeam.setStatus("LOST");
            secondTeam.setStatus("WON");
            teamServiceImpl.updateStatus("LOST", firstTeam.getTeamId());
            teamServiceImpl.updateStatus("WON", secondTeam.getTeamId());
            losingTeam.add(firstTeam);
            logger.info("{} , {} status", firstTeam.getStatus(), secondTeam.getStatus());
        }
    }

    @Override
    public int randomFunction() {
        return (int) (Math.random() * 8);
    }

    @Override
    public int Batting(Team team, int score, List<Scorecard> scorecardList, List<ScoreCardList> sl, int length) {
        List<Player> pl = playerServiceimpl.enterPlayer(team);
        pl.forEach(player -> logger.info(Integer.toString(player.getPlayerId())));
        logger.info(Integer.toString(length));
        int i = 0;
        int totalScore = 0;
        int over = 0;
        int prevScore = 0;
        int current_batsmen = 1;
        int wicket = 0;
        while (i < 11 && over < 60) {
            Scorecard scorecard = new Scorecard();
            scorecard.setBattingTeam(team);
            int currBallScore = randomFunction();
            if (currBallScore == 7) {
                scorecard.setRunsOnCurrBall("W");
            } else {
                scorecard.setRunsOnCurrBall(Integer.toString(currBallScore));
            }
            if (over != 0 && over % 12 == 0) {
                logger.info("{} over", over);
                addListScore(over, length, scorecardList, sl);
            }
            current_batsmen = i;
            if (totalScore > score) {
                pl.get(current_batsmen).setScore(prevScore + "*");
                playerServiceimpl.addScore(pl.get(current_batsmen).getScore(), pl.get(current_batsmen).getPlayerId());
                addListScore(over, length, scorecardList, sl);
                return totalScore;
            }
            if (currBallScore == 7) {
                pl.get(current_batsmen).setScore(Integer.toString(prevScore));
                playerServiceimpl.addScore(pl.get(current_batsmen).getScore(), pl.get(current_batsmen).getPlayerId());
                prevScore = 0;
                i++;
                over++;
                wicket++;
                String str = over / 6 + ".";
                str += Integer.toString(over % 6);
                scorecard.setOver(str);
                scorecard.setWicket(wicket);
                scorecard.setScore(totalScore);
                scorecardRepo.save(scorecard);
                scorecardList.add(scorecard);
                continue;
            }
            totalScore += currBallScore;
            prevScore += currBallScore;
            over++;
            String str = over / 6 + ".";
            str += Integer.toString(over % 6);
            scorecard.setOver(str);
            scorecard.setWicket(wicket);
            scorecard.setScore(totalScore);
            scorecardRepo.save(scorecard);
            scorecardList.add(scorecard);
        }
        if (i != 11) {
            pl.get(current_batsmen).setScore(prevScore + "*");
            playerServiceimpl.addScore(pl.get(current_batsmen).getScore(), pl.get(current_batsmen).getPlayerId());
        }
        addListScore(over, length, scorecardList, sl);
        return totalScore;
    }

    public Tournament addMatch(Team firstTeam, Team secondTeam, int size, List<ScoreCardList> scl) {
        Match match = new Match();
        if (firstTeam.getStatus().equals("WON")) {
            match.setWinner(firstTeam.getTeam_name());
            match.setLoser(secondTeam.getTeam_name());
        }
        else {
            match.setWinner(secondTeam.getTeam_name());
            match.setLoser(firstTeam.getTeam_name());
        }
        match.setScoreboard(scl);
        logger.info(match.toString());
        matchServiceImpl.addMatch(match);
        return addTournament(size, match);
    }
    public void addListScore(int over, int length, List<Scorecard> scorecardList, List<ScoreCardList> sl) {
        List<Scorecard> sc = new ArrayList<>();

        for (int k = (over + length) - (over % 12 == 0 ? 12 : over % 12); k < (over + length); k++) {
            sc.add(scorecardList.get(k));
        }
        ScoreCardList scl = new ScoreCardList();
        scl.setScorecardList(sc);
        scoreCardListServiceImpl.addScoreCardList(scl);
        sl.add(scl);
    }
}


