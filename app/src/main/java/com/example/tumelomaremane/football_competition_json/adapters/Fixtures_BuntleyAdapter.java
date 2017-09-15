package com.example.tumelomaremane.football_competition_json.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tumelomaremane.football_competition_json.R;
import com.example.tumelomaremane.football_competition_json.models.Fixtures_Buntley;

import java.util.ArrayList;

/**
 * Created by tumelomaremane on 2017/09/04.
 */

public class Fixtures_BuntleyAdapter extends RecyclerView.Adapter<Fixtures_BuntleyAdapter.FixtureHolder>
{

    public ArrayList<Fixtures_Buntley> fData;
    private Activity fACtivity;


    public Fixtures_BuntleyAdapter(ArrayList<Fixtures_Buntley> data, Activity activity) {
        this.fData = data;
        this.fACtivity = activity;
    }

    @Override
    public FixtureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fixture_card, parent, false);
        return new FixtureHolder(view);
    }

    @Override
    public void onBindViewHolder(final FixtureHolder holder, int position) {
        Fixtures_Buntley fixtures_Buntley = fData.get(position);

        holder.setHomeTeamName(fixtures_Buntley.getHomeTeamName());
        holder.setAwayTeamName(fixtures_Buntley.getAwayTeamName());
        holder.setDate(fixtures_Buntley.getDate());
        holder.setStatus(fixtures_Buntley.getStatus());

        /*holder.txtPlayers.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popupMenu = new PopupMenu(mACtivity, holder.txtPlayers);
                        popupMenu.inflate(R.menu.menu_team_links);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                        {
                            @Override
                            public boolean onMenuItemClick(MenuItem item)
                            {
                              switch (item.getItemId())
                              {


                              }
                              return false;
                            }

                        });

                        //Toast.makeText(mACtivity, holder.teamName.getText(), Toast.LENGTH_LONG).show();
                    }
                }
        );*/



    }


    @Override
    public int getItemCount() {
        /*if (tData == null)
            return 0;*/
        return fData.size();
    }

    public class FixtureHolder extends RecyclerView.ViewHolder {

        TextView homeTeamName;
        TextView awayTeamName;
        TextView date;
        TextView status;

        public FixtureHolder(View itemView) {
            super(itemView);

            homeTeamName = itemView.findViewById(R.id.txtHome_team_name);
            awayTeamName = itemView.findViewById(R.id.txtAway_team_name);
            date = itemView.findViewById(R.id.txtDate);
            status = itemView.findViewById(R.id.txtStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
//                        Recipe clickedDataItem = recipeList.get(pos);
//
//                        Intent intent = new Intent(mContext, RecipeDetailActivity.class);
//                        intent.putExtra("Recipe", clickedDataItem);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mContext.startActivity(intent);
//                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        public void setHomeTeamName(String HomeTeamName) {
            homeTeamName.setText(HomeTeamName);
        }

        public void setAwayTeamName(String AwayTeamName) {
            awayTeamName.setText(AwayTeamName);
        }

        public void setDate(String Date) {
            date.setText(Date);
        }

        public void setStatus(String Status) {
            status.setText(Status);
        }

    }
}
