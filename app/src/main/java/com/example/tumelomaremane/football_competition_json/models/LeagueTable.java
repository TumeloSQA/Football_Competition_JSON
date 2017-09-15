package com.example.tumelomaremane.football_competition_json.models;

import java.util.List;

/**
 * Created by tumelomaremane on 2017/09/10.
 */

public class LeagueTable
{

    private String leagueCaption;
    private int matchday;
    private List<Standings> standing;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public LeagueTable setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
        return this;
    }

    public int getMatchday() {
        return matchday;
    }

    public LeagueTable setMatchday(int matchday) {
        this.matchday = matchday;
        return this;
    }

    public List<Standings> getStanding() {
        return standing;
    }

    public LeagueTable setStanding(List<Standings> standing) {
        this.standing = standing;
        return this;
    }

}
