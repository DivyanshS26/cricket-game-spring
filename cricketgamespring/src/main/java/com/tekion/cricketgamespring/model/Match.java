package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany
    private List<ScoreCardList> scoreboard;
    @Column(name = "winner")
    private String winner;
    @Column(name = "loser")
    private String loser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public List<ScoreCardList> getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(List<ScoreCardList> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public int getMatch_id() {
        return id;
    }

    public void setMatch_id(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", scoreboard=" + scoreboard +
                ", matchWinner='" + winner + '\'' +
                '}';
    }
}
