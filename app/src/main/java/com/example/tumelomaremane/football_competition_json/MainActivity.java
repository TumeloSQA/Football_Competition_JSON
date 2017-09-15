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

import com.example.tumelomaremane.football_competition_json.adapters.TeamsAdapter;
import com.example.tumelomaremane.football_competition_json.models.Teams;

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

public class MainActivity extends Activity {

    //recyclerview objects
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerView mTeamRecyclerView;
    private TeamsAdapter tAdapter;
    private ArrayList<Teams> mTeamsCollection;
    private ActionMode currentActionMode;

    public static int LEAGUE_ID = 426;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mTeamRecyclerView = findViewById(R.id.team_recycler);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {

            mTeamRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else
        {
            mTeamRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }


        init();

        new FetchDataTask().execute();
    }







    public class FetchDataTask extends AsyncTask<Void, Void, Void> {
        private String teamString;

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.teams_api));
            URL url;

            try {
                //Uri builtUri = Uri.parse("http://api.football-data.org/v1/competitions/445/teams");

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

                teamString = buffer.toString();
                JSONObject jsonObject = new JSONObject(teamString);

                Log.v("Response", jsonObject.toString());

                JSONArray restaurantsArray = jsonObject.getJSONArray("teams");
                //list = new ArrayList<>();
                for (int i = 0; i < restaurantsArray.length(); i++) {

                    Log.v("BRAD_", i + "");
                    String [] links = new String[2];

                    String name;
                    String code;
                    String shortName;
                    String crestUrl;
                    String players;


                    JSONObject jTeams = (JSONObject) restaurantsArray.get(i);
                    JSONObject jsonLinks = jTeams.getJSONObject("_links");


                    name = jTeams.getString("name");
                    code = jTeams.getString("code");
                    shortName = jTeams.getString("shortName");
                    crestUrl = jTeams.getString("crestUrl");

                    //Retrieve Teams links(players, fixtures)
                    JSONObject jFixtures = jsonLinks.getJSONObject("fixtures");
                    JSONObject jPlayers = jsonLinks.getJSONObject("players");


                    links[0] = jFixtures.toString();
                    links[1] = jPlayers.toString();
                    players = links[1];


                    Teams team = new Teams();
                    team.setName(name);
                    team.setCode(code);
                    team.setShortName(shortName);
                    team.setCrestUrl(crestUrl);
                    team.setPlayers(players);

                    mTeamsCollection.add(team);
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
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            tAdapter.notifyDataSetChanged();
        }
    }


    private void init() {
        mTeamRecyclerView = (RecyclerView) findViewById(R.id.team_recycler);
        //mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTeamRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTeamRecyclerView.setHasFixedSize(true);
        mTeamsCollection = new ArrayList<>();
        tAdapter = new TeamsAdapter(mTeamsCollection, this);
        mTeamRecyclerView.setAdapter(tAdapter);
    }



    @Override
    protected void onStart() {
        super.onStart();

        new FetchDataTask().execute();
    }
}
