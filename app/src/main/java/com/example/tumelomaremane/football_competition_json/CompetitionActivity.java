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

import com.example.tumelomaremane.football_competition_json.adapters.CompetitionAdapter;
import com.example.tumelomaremane.football_competition_json.models.Competition;

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


public class CompetitionActivity extends Activity {

    //recyclerview objects
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerView mCompRecyclerView;
    private CompetitionAdapter compAdapter;
    private ArrayList<Competition> mCompCollection;
    private ActionMode currentActionMode;

    public static int LEAGUE_ID = 426;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);


        mCompRecyclerView = findViewById(R.id.team_recycler);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            mCompRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mCompRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }


        init();

        new FetchDataTask().execute();
    }


    public class FetchDataTask extends AsyncTask<Void, Void, Void> {
        private String compString;

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.comp_api));
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

                compString = buffer.toString();
                JSONObject jsonObject = new JSONObject(compString);

                Log.v("Response", jsonObject.toString());

                JSONArray competitionsArray = jsonObject.getJSONArray(jsonObject.toString());
                //list = new ArrayList<>();
                for (int i = 0; i < competitionsArray.length(); i++) {

                    Log.v("BRAD_", i + "");

                    String caption;
                    String year;
                    Integer noOfTeams;


                    JSONObject jComp = competitionsArray.getJSONObject(i);

                    caption = jComp.getString("caption");
                    year = jComp.getString("year");
                    noOfTeams = jComp.getInt("numberOfTeams");


                    Competition competition = new Competition();
                    competition.setCaption(caption);
                    competition.setYear(year);
                    competition.setNumberOfTeams(noOfTeams);


                    mCompCollection.add(competition);
                }
            } catch
                    (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("CompetitionActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            compAdapter.notifyDataSetChanged();
        }
    }


    private void init() {
        mCompRecyclerView = findViewById(R.id.com_recycler);
        //mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCompRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mCompRecyclerView.setHasFixedSize(true);
        mCompCollection = new ArrayList<>();
        compAdapter = new CompetitionAdapter(mCompCollection, this);
        mCompRecyclerView.setAdapter(compAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new FetchDataTask().execute();
    }
}
