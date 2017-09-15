package com.example.tumelomaremane.football_competition_json;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;

import com.example.tumelomaremane.football_competition_json.adapters.PlayersAdapter;
import com.example.tumelomaremane.football_competition_json.models.Players;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlayersActivity extends Activity {

    //recyclerview objects
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerView mPlayerRecyclerView;
    private PlayersAdapter pAdapter;
    private ArrayList<Players> mPlayerCollection;
    private ActionMode currentActionMode;
    public String playersURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        mPlayerRecyclerView = findViewById(R.id.player_recycler);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {

            mPlayerRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else
        {
            mPlayerRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }


       /* TextView txtView = findViewById(R.id.textView2);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
       // if(bd != null)
        //{
            String getName = (String) bd.get("playersUrl");
            txtView.setText(getName);
       // }
        playersURL = getName;*/
        init();

        new FetchDataTask().execute();

    }


    public class FetchDataTask extends AsyncTask<Void, Void, Void> {
        private String playerString;

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.players_api));

            URL url;

            try {

                url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-Auth-Token", "99f155e8e65b42fb9d4719d1a2267346");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    //Nothing to do
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                playerString = buffer.toString();
                JSONObject jsonObject = new JSONObject(playerString);

                Log.v("Response", jsonObject.toString());

                JSONArray playersArray = jsonObject.getJSONArray("players");
                //list = new ArrayList<>();
                for (int i = 0; i < playersArray.length(); i++) {

                    Log.v("BRAD_", i + "");

                    String name;
                    String position;
                    Integer jerseyNumber;
                    String dateOB;
                    String nationality;
                    String contractUNTIL;


                    JSONObject jPlayers = (JSONObject) playersArray.get(i);

                    name = jPlayers.getString("name");
                    position = jPlayers.getString("position");
                    jerseyNumber = jPlayers.getInt("jerseyNumber");
                    nationality = jPlayers.getString("nationality");
                    dateOB = jPlayers.getString("dateOfBirth");
                    contractUNTIL = jPlayers.getString("contractUntil");


                    Players player = new Players();
                    player.setName(name);
                    player.setPosition(position);
                    player.setJerseyNumber(jerseyNumber);
                    player.setNationality(nationality);
                    player.setDateOfBirth(dateOB);
                    player.setDateOfBirth(contractUNTIL);


                    mPlayerCollection.add(player);
                }
            } catch
                    (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlayersActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            pAdapter.notifyDataSetChanged();
        }
    }


    private void init() {
        mPlayerRecyclerView = findViewById(R.id.player_recycler);
        //mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPlayerRecyclerView.setHasFixedSize(true);
        mPlayerCollection = new ArrayList<>();
        pAdapter = new PlayersAdapter(mPlayerCollection, this);
        mPlayerRecyclerView.setAdapter(pAdapter);
    }



    @Override
    protected void onStart() {
        super.onStart();

        new FetchDataTask().execute();
    }

}
