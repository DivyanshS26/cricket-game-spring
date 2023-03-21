package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne
    private Team team;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "score")
    private String score;

    public int getPlayerId() {
        return id;
    }

    public void setPlayerId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + id +
                ", team=" + team +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}
