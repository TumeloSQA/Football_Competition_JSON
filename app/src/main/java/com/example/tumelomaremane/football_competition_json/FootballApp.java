package com.example.tumelomaremane.football_competition_json;

import android.app.Application;

import com.example.tumelomaremane.football_competition_json.api.Client;

/**
 * Created by tumelomaremane on 2017/09/10.
 */

public class FootballApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Client.setApiKey("99f155e8e65b42fb9d4719d1a2267346");
    }
}
