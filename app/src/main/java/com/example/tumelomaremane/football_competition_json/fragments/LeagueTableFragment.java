package com.example.tumelomaremane.football_competition_json.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tumelomaremane.football_competition_json.adapters.LeagueTableAdapter;
import com.example.tumelomaremane.football_competition_json.api.Client;
import com.example.tumelomaremane.football_competition_json.models.LeagueTable;
import com.example.tumelomaremane.football_competition_json.models.Standings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tumelomaremane on 2017/09/11.
 */

public class LeagueTableFragment extends ListFragment
{

    private Client client;

    private int mCompetitionId;
    private List<Standings> mLeagueTableRows;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT > 21) {
            getListView().setNestedScrollingEnabled(true);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        client = Client.getClient();
        loadData();
    }

    private void loadData() {
        client.leagueTableForCompetition(mCompetitionId).enqueue(new Callback<LeagueTable>() {
            @Override
            public void onResponse(Call<LeagueTable> call, Response<LeagueTable> response) {
                Log.v("Test", response.message());
                mLeagueTableRows = response.body().getStanding();
                showData();
            }

            @Override
            public void onFailure(Call<LeagueTable> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData() {
        setListAdapter(new LeagueTableAdapter(getActivity(), mLeagueTableRows));
    }


    public static Fragment newInstance(int competitionId) {
        LeagueTableFragment fragment = new LeagueTableFragment();
        fragment.mCompetitionId = competitionId;
        return fragment;
    }
}
