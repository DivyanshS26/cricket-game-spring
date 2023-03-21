package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ScoreCardList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<Scorecard> scorecardList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Scorecard> getScorecardList() {
        return scorecardList;
    }

    public void setScorecardList(List<Scorecard> scorecardList) {
        this.scorecardList = scorecardList;
    }

    @Override
    public String toString() {
        return "ScoreCardList{" +
                "id=" + id +
                ", scorecardList=" + scorecardList +
                '}';
    }
}
