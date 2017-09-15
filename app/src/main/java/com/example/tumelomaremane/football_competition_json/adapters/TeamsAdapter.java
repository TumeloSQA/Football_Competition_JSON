package com.example.tumelomaremane.football_competition_json.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tumelomaremane.football_competition_json.FixtureActivity;
import com.example.tumelomaremane.football_competition_json.R;
import com.example.tumelomaremane.football_competition_json.models.Teams;

import java.util.ArrayList;

/**
 * Created by tumelomaremane on 2017/08/29.
 */

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamHolder> {
    public ArrayList<Teams> tData;
    private Activity mActivity;


    public TeamsAdapter(ArrayList<Teams> data, Activity activity) {
        this.tData = data;
        this.mActivity = activity;
    }

    @Override
    public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_card, parent, false);
        return new TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamHolder holder, int position) {
        Teams team = tData.get(position);

        holder.setName("Team Name: " + team.getName());
        holder.setTeamCode("Code: " + team.getCode());
        holder.setTeamPlayers(team.getPlayers());

        Glide.with(mActivity)
                .load(team.getCrestUrl())
                .placeholder(R.drawable.premierleague)

                .into(holder.teamImageView);


        holder.teamImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopupMenu(holder.teamImageView);

            }
        });


    }


    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mActivity, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_team_links, popup.getMenu());
        TextView etName1 = view.findViewById(R.id.txtPlayersUrl);
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());

        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_players:


                    Intent intent = new Intent(mActivity, FixtureActivity.class);
                    TextView playersUrl = mActivity.findViewById(R.id.txtPlayersUrl);
                    //intent.putExtra("playersUrl", playersUrl.getText());
                    mActivity.startActivity(intent);
                    Toast.makeText(mActivity, "Players", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.menu_fixtures:
                    Intent intentFixtures = new Intent(mActivity, FixtureActivity.class);
                    mActivity.startActivity(intentFixtures);
                    Toast.makeText(mActivity, "Fixtures", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }

        }
    }



    @Override
    public int getItemCount() {
        /*if (tData == null)
            return 0;*/
        return tData.size();
    }

    public class TeamHolder extends RecyclerView.ViewHolder {

        ImageView teamImageView;
        TextView teamName;
        TextView teamCode;
        TextView teamPlayers;
        TextView playersUrl;

        public TeamHolder(View itemView) {
            super(itemView);

            teamImageView = itemView.findViewById(R.id.crestUrl);
            teamName = itemView.findViewById(R.id.txtName);
            teamCode = itemView.findViewById(R.id.txtCode);
            teamPlayers = itemView.findViewById(R.id.menu_players);
            playersUrl = itemView.findViewById(R.id.txtPlayersUrl);


        }

        public void setName(String name) {
            teamName.setText(name);
        }

        public void setTeamCode(String code) {
            teamCode.setText(code);
        }
        public void setTeamPlayers(String players) {
            playersUrl.setText(players);
        }


    }

}
