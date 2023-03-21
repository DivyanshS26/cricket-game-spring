package com.tekion.cricketgamespring.service;

import com.tekion.cricketgamespring.model.ScoreCardList;
import com.tekion.cricketgamespring.model.Scorecard;
import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.model.Tournament;

import java.util.List;

public interface ScorecardService {
    public Tournament selectTwoTeams(List<Team> team);

    public int Batting(Team team, int score, List<Scorecard> scorecardList, List<ScoreCardList> sl, int length);

    Tournament startMatch(List<Team> team, Team firstTeam, Team secondTeam, List<Team> losingTeam);

    void won(int firstTeamScore, int secondTeamScore, Team firstTeam, Team secondTeam, List<Team> losingTeam);

    public int randomFunction();

}
