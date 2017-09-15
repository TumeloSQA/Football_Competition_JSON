package com.example.tumelomaremane.football_competition_json.models;

/**
 * Created by tumelomaremane on 2017/09/10.
 */

public class Results
{
    private Integer goalsHomeTeam;
    private Integer goalsAwayTeam;
    private Results halfTime;

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public Results setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
        return this;
    }

    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public Results setGoalsAwayTeam(Integer goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
        return this;
    }

    public Results getHalfTime() {
        return halfTime;
    }

    public Results setHalfTime(Results halfTime) {
        this.halfTime = halfTime;
        return this;
    }
}
