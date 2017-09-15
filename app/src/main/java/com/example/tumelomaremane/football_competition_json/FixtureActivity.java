package com.example.tumelomaremane.football_competition_json;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;

import com.example.tumelomaremane.football_competition_json.adapters.Fixtures_BuntleyAdapter;
import com.example.tumelomaremane.football_competition_json.models.Fixtures_Buntley;

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

/**
 * Created by tumelomaremane on 2017/09/04.
 */

public class FixtureActivity extends Activity
{

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerView mTeamRecyclerView;
    private Fixtures_BuntleyAdapter fAdapter;
    private ArrayList<Fixtures_Buntley> mTeamsCollection;
    private ActionMode currentActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture);



        init();

        new FetchDataTask().execute();
    }

    public class FetchDataTask extends AsyncTask<Void, Void, Void> {
        private String fixtureString;

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.fixture_api));
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

                fixtureString = buffer.toString();
                JSONObject jsonObject = new JSONObject(fixtureString);

                Log.v("Response", jsonObject.toString());

                JSONArray restaurantsArray = jsonObject.getJSONArray("fixtures");

                //list = new ArrayList<>();
                for (int i = 0; i < restaurantsArray.length(); i++) {

                    Log.v("BRAD_", i + "");
                    String date;
                    String status;
                    String homeTeamName;
                    String awayTeamName;


                    JSONObject jTeams = (JSONObject) restaurantsArray.get(i);
                    //jTeams = jTeams.getJSONObject("teams");


                    date = jTeams.getString("date");
                    status = jTeams.getString("status");
                    homeTeamName = jTeams.getString("homeTeamName");
                    awayTeamName = jTeams.getString("awayTeamName");


                    Fixtures_Buntley fixtures_Buntley = new Fixtures_Buntley();
                    fixtures_Buntley.setDate(date);
                    fixtures_Buntley.setStatus(status);
                    fixtures_Buntley.setHomeTeamName(homeTeamName);
                    fixtures_Buntley.setAwayTeamName(awayTeamName);

                    mTeamsCollection.add(fixtures_Buntley);
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
                        Log.e("FixtureActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            fAdapter.notifyDataSetChanged();
        }
    }


    private void init() {
        mTeamRecyclerView = findViewById(R.id.fixture_recycler);
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTeamRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTeamRecyclerView.setHasFixedSize(false);
        mTeamsCollection = new ArrayList<>();
        fAdapter = new Fixtures_BuntleyAdapter(mTeamsCollection, this);
        mTeamRecyclerView.setAdapter(fAdapter);
    }



    @Override
    protected void onStart() {
        super.onStart();

        new FetchDataTask().execute();
    }


}
