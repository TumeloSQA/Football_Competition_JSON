package com.example.tumelomaremane.football_competition_json.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tumelomaremane.football_competition_json.R;
import com.example.tumelomaremane.football_competition_json.models.Players;

import java.util.ArrayList;

/**
 * Created by tumelomaremane on 2017/09/07.
 */

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerHolder> {
    public ArrayList<Players> pData;
    private Activity mACtivity;

    public PlayersAdapter(ArrayList<Players> data, Activity activity) {
        this.pData = data;
        this.mACtivity = activity;
    }

    @Override
    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.players_card, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlayerHolder holder, int position) {
        Players players = pData.get(position);

        holder.setName("Player Name: " + players.getName());
        holder.setPosition("Position: " + players.getPosition());
        //holder.setJerseyNumber("Jersey No: " + players.getJerseyNumber());
        holder.setDOB("DOB: " + players.getDateOfBirth());
        holder.setNationality("Nationality: " + players.getNationality());
        holder.setContractEnd("Contract End: " + players.getContractUntil());


    }

    @Override
    public int getItemCount() {
        /*if (tData == null)
            return 0;*/
        return pData.size();
    }

    public class PlayerHolder extends RecyclerView.ViewHolder {

        TextView playerName;
        TextView playerPosition;
        TextView jerseyNumber;
        TextView dateOfBirth;
        TextView nationality;
        TextView contractEnd;

        public PlayerHolder(View itemView) {
            super(itemView);


            playerName = itemView.findViewById(R.id.txtPlayerName);
            playerPosition = itemView.findViewById(R.id.txtPosition);
            //jerseyNumber = itemView.findViewById(R.id.txtJerseyNumber);
            dateOfBirth = itemView.findViewById(R.id.txtDOB);
            nationality = itemView.findViewById(R.id.txtNationality);
            contractEnd = itemView.findViewById(R.id.txtContractEnd);
        }



        public void setName(String name) {
            playerName.setText(name);
        }

        public void setPosition(String position) {
            playerPosition.setText(position);
        }

        public void setJerseyNumber(int JerseyNumber) {
            jerseyNumber.setText(JerseyNumber);
        }

        public void setDOB(String dob) {
            dateOfBirth.setText(dob);
        }

        public void setNationality(String Nationality) {
            nationality.setText(Nationality);
        }

        public void setContractEnd(String contractUntil) {
            contractEnd.setText(contractUntil);
        }
    }

}
