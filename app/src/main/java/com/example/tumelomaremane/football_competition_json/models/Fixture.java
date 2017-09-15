package com.example.tumelomaremane.football_competition_json.models;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by tumelomaremane on 2017/09/10.
 */

public class Fixture
{
    private Date date;
    private Status status;
    private int matchday;
    private String homeTeamName;
    private String awayTeamName;
    private Results result;

    public enum Status {
        SCHEDULED, TIMED, IN_PLAY, FINISHED, POSTPONED, CANCELED
    }

    public Date getDate() {
        return date;
    }

    public Fixture setDate(Date date) {
        this.date = date;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Fixture setStatus(Status status) {
        this.status = status;
        return this;
    }

    public int getMatchday() {
        return matchday;
    }

    public Fixture setMatchday(int matchday) {
        this.matchday = matchday;
        return this;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public Fixture setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
        return this;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public Fixture setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
        return this;
    }

    public Results getResult() {
        return result;
    }

    public Fixture setResult(Results result) {
        this.result = result;
        return this;
    }


    public static final Comparator<Fixture> DATE_COMPARATOR = new Comparator<Fixture>() {
        @Override
        public int compare(Fixture fixture, Fixture t1) {
            return -fixture.getDate().compareTo(t1.getDate());
        }
    };

}
