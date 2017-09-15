package com.example.tumelomaremane.football_competition_json.models;

import java.util.Date;

/**
 * Created by tumelomaremane on 2017/08/29.
 */

public class Competition
{
    private int id;
    private String caption;
    private String league;
    private String year;
    private int currentMatchday;
    private int numberOfMatchdays;
    private int numberOfTeams;
    private int numberOfGames;
    private Date lastUpdated;


    public int getId() {
        return id;
    }

    public Competition setId(int id) {
        this.id = id;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Competition setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getLeague() {
        return league;
    }

    public Competition setLeague(String league) {
        this.league = league;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Competition setYear(String year) {
        this.year = year;
        return this;
    }

    public int getCurrentMatchday() {
        return currentMatchday;
    }

    public Competition setCurrentMatchday(int currentMatchday) {
        this.currentMatchday = currentMatchday;
        return this;
    }

    public int getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public Competition setNumberOfMatchdays(int numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
   return this;
    }


    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
