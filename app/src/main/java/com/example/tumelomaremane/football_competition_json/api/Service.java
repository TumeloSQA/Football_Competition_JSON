package com.example.tumelomaremane.football_competition_json.api;

import com.example.tumelomaremane.football_competition_json.models.Competition;
import com.example.tumelomaremane.football_competition_json.models.Fixture;
import com.example.tumelomaremane.football_competition_json.models.LeagueTable;
import com.example.tumelomaremane.football_competition_json.models.Player;
import com.example.tumelomaremane.football_competition_json.models.Team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tumelomaremane on 2017/08/30.
 */

public interface Service
{
    @GET("competitions")
    Call<List<Competition>> competitions();

    @GET("competitions/{competitionId}/teams")
    Call<ArrayList<Team>> teamsForCompetition(@Path("competitionId") int competitionId);

    @GET("competitions/{competitionId}/leagueTable")
    Call<LeagueTable> leagueTableForCompetition(@Path("competitionId") int competitionId);

    @GET("fixtures")
    Call<ArrayList<Fixture>> fixtures();


    @GET("competitions/{competitionId}/fixtures")
    Call<ArrayList<Fixture>> fixturesForCompetition(@Path("competitionId") int competitionId);

    @GET("teams/{teamId}/fixtures")
    Call<ArrayList<Fixture>> fixturesForTeam(@Path("teamId") int teamId);

    @GET("teams/{teamId}")
    Call<Team> team(@Path("teamId") int teamId);

    @GET("teams/{teamId}/players")
    Call<ArrayList<Player>> playersForTeam(@Path("teamId") int teamId);

}
