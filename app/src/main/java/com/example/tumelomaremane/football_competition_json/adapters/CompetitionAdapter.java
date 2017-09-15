package com.example.tumelomaremane.football_competition_json.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tumelomaremane.football_competition_json.CompetitionActivity;
import com.example.tumelomaremane.football_competition_json.R;
import com.example.tumelomaremane.football_competition_json.models.Competition;

import java.util.ArrayList;

/**
 * Created by tumelomaremane on 2017/08/30.
 */

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionHolder> {

    public ArrayList<Competition> tData;
    private Activity mActivity;

    public CompetitionAdapter(ArrayList<Competition> mContext, CompetitionActivity competitonList) {
        this.mActivity = competitonList;
        this.tData = mContext;
    }

    @Override
    public CompetitionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.competition_card, parent, false);

        return new CompetitionHolder(view);
    }



    @Override
    public void onBindViewHolder(CompetitionHolder holder, int position) {
        Competition competition = tData.get(position);
        holder.caption.setText(competition.getCaption());
        holder.league.setText(competition.getLeague());
        holder.noOfTeams.setText(competition.getYear());

    }

    @Override
    public int getItemCount(){
        return tData.size();

    }

    public class CompetitionHolder extends RecyclerView.ViewHolder{
        public TextView caption;
        public TextView league;
        public TextView noOfTeams;


        public CompetitionHolder(View view){

            super(view);
            caption = view.findViewById(R.id.txtCaption);
            league = view.findViewById(R.id.txtYear);
            noOfTeams = view.findViewById(R.id.txtNoOfTeams);


            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
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
    }


}
