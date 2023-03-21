package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

@Entity
public class Scorecard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Team battingTeam;
    //    @OneToOne
//    private Player batsman;
    private String runsOnCurrBall;
    @Column(name = "score")
    private int score;
    @Column(name = "wicket")
    private int wicket;
    @Column(name = "over")
    private String over;

    public int getScorecardId() {
        return id;
    }

    public void setScorecardId(int id) {
        this.id = id;
    }

//    public Player getBatsman() {
//        return batsman;
//    }
//
//    public void setBatsman(Player batsman) {
//        this.batsman = batsman;
//    }

    public String getRunsOnCurrBall() {
        return runsOnCurrBall;
    }

    public void setRunsOnCurrBall(String runsOnCurrBall) {
        this.runsOnCurrBall = runsOnCurrBall;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWicket() {
        return wicket;
    }

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    @Override
    public String toString() {
        return "Scorecard{" +
                "scorecardId=" + id +
                ", battingTeam=" + battingTeam +
//                ", batsman=" + batsman +
                ", runsOnCurrBall=" + runsOnCurrBall +
                ", score=" + score +
                ", wicket=" + wicket +
                ", over='" + over + '\'' +
                '}';
    }
}
