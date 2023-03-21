package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "winningTeam")
    private String winningTeam;

    @OneToMany
    private List<Match> match;

    public List<Match> getMatch() {
        return match;
    }

    public void setMatch(List<Match> match) {
        this.match = match;
    }

    public int getTournamentId() {
        return id;
    }

    public void setTournamentId(int id) {
        this.id = id;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentId=" + id +
                ", winningTeam='" + winningTeam + '\'' +
                ", match=" + match +
                '}';
    }
}
