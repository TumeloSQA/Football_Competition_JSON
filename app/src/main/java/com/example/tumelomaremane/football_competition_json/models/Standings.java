package com.example.tumelomaremane.football_competition_json.models;

import java.net.URL;

/**
 * Created by tumelomaremane on 2017/09/10.
 */

public class Standings
{
    private int position;
    private String teamName;
    private URL crestURI;
    private int playedGames;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;
    private int wins;
    private int draws;
    private int losses;

    public int getPosition() {
        return position;
    }

    public Standings setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public Standings setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public URL getCrestURI() {
        return crestURI;
    }

    public Standings setCrestURI(URL crestURI) {
        this.crestURI = crestURI;
        return this;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public Standings setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public Standings setPoints(int points) {
        this.points = points;
        return this;
    }

    public int getGoals() {
        return goals;
    }

    public Standings setGoals(int goals) {
        this.goals = goals;
        return this;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public Standings setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
        return this;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public Standings setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public Standings setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public int getDraws() {
        return draws;
    }

    public Standings setDraws(int draws) {
        this.draws = draws;
        return this;
    }

    public int getLosses() {
        return losses;
    }

    public Standings setLosses(int losses) {
        this.losses = losses;
        return this;
    }





}
