package com.sumedha.CricStatify.model;

import jakarta.persistence.*;


import jakarta.persistence.Id;

@Entity
public class PlayerStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private int ballsFaced;
    private int runsScored;
    private int wicketsTaken;
    private float oversBowled;
    private float strikeRate;
    private float battingAverage;
    private float economyRate;
    private float bowlingAverage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public float getOversBowled() {
        return oversBowled;
    }

    public void setOversBowled(float oversBowled) {
        this.oversBowled = oversBowled;
    }

    public float getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(float strikeRate) {
        this.strikeRate = strikeRate;
    }

    public float getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(float battingAverage) {
        this.battingAverage = battingAverage;
    }

    public float getEconomyRate() {
        return economyRate;
    }

    public void setEconomyRate(float economyRate) {
        this.economyRate = economyRate;
    }

    public float getBowlingAverage() {
        return bowlingAverage;
    }

    public void setBowlingAverage(float bowlingAverage) {
        this.bowlingAverage = bowlingAverage;
    }

    // Getters and Setters
    // ...
}
