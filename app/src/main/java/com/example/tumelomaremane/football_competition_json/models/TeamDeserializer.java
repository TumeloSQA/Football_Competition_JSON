package com.example.tumelomaremane.football_competition_json.models;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by tumelomaremane on 2017/09/10.
 */

public class TeamDeserializer implements JsonDeserializer<Team>
{

    @Override
    public Team deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject teamObj = json.getAsJsonObject();
        Team team = new Team();
        String self = teamObj.getAsJsonObject("_links").getAsJsonObject("self").get("href").getAsString();
        int id = Integer.parseInt(self.substring(self.lastIndexOf("/") + 1, self.length()));
        team.setId(id);
        team.setName(teamObj.get("name").getAsString());
        if (teamObj.has("code") && !teamObj.get("code").isJsonNull()) {
            team.setCode(teamObj.get("code").getAsString());
        }
        team.setCrestUrl(teamObj.get("crestUrl").getAsString());
        team.setShortName(teamObj.get("shortName").getAsString());
        return team;
    }

}
