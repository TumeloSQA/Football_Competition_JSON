package com.example.tumelomaremane.football_competition_json.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tumelomaremane.football_competition_json.R;
import com.example.tumelomaremane.football_competition_json.models.Competition;

import java.util.ArrayList;

/**
 * Created by tumelomaremane on 2017/08/30.
 */

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.CompetitionHolder>
{
    private ArrayList<Competition> cData;
    private Activity compActivity;

    public CompetitionsAdapter(ArrayList<Competition> data, Activity activity) {
        this.cData = data;
        this.compActivity = activity;
    }

    @Override
    public CompetitionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.competition_card, parent, false);
        return new CompetitionHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionHolder holder, int position) {
        Competition competition = cData.get(position);

        holder.setCaption(competition.getCaption());
        holder.setCompLeague(competition.getLeague());
        holder.setCompYear(competition.getYear());
    }


    @Override
    public int getItemCount() {
        if (cData == null)
            return 0;
        return cData.size();
    }

    public class CompetitionHolder extends RecyclerView.ViewHolder {


        TextView compCaption;
        TextView compYear;
        TextView league;

        public CompetitionHolder(View itemView) {
            super(itemView);


            compCaption = itemView.findViewById(R.id.txtCaption);
            compYear = itemView.findViewById(R.id.txtYear);
            league = itemView.findViewById(R.id.txtNoOfTeams);
        }

        public void setCaption(String caption) {
            compCaption.setText(caption);
        }

        public void setCompYear(String year) {
            compYear.setText(year);
        }

        public void setCompLeague(String leagues) {
            league.setText(leagues);
        }


    }
}
