package com.example.tumelomaremane.football_competition_json.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tumelomaremane.football_competition_json.R;
import com.example.tumelomaremane.football_competition_json.models.Standings;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tumelomaremane on 2017/09/11.
 */

public class LeagueTableAdapter extends ArrayAdapter<Standings>
{

    public LeagueTableAdapter(Context context, List<Standings> objects) {
        super(context, R.layout.league_table_card, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        Standings standing = getItem(position);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.league_table_card, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.position.setText(Integer.toString(standing.getPosition()));
        holder.team.setText(standing.getTeamName());
        holder.points.setText(Integer.toString(standing.getPoints()));
        holder.matchPlayed.setText(Integer.toString(standing.getPlayedGames()));
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.standing_position)
        TextView position;
        @BindView(R.id.standing_team)
        TextView team;
        @BindView(R.id.standing_mp)
        TextView matchPlayed;
        @BindView(R.id.standing_points)
        TextView points;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
