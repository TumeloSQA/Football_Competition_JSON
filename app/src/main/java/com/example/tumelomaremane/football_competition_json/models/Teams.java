package com.example.tumelomaremane.football_competition_json.models;

/**
 * Created by tumelomaremane on 2017/08/29.
 */

public class Teams
{
    private String name;
    private String code;
    private String shortName;
    private String crestUrl;
    private String [] _links;
    private String players;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }
    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }
    public String[] get_links() {
        return _links;
    }

    public void set_links(String[] _links) {
        this._links = _links;
    }
}
