package com.example.tumelomaremane.football_competition_json.models;

import java.util.Comparator;

/**
 * Created by tumelomaremane on 2017/09/10.
 */

public class Team
{

    private int id;
    private String name;
    private String code;
    private String shortName;
    private String crestUrl;

    public int getId() {
        return id;
    }

    public Team setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Team setCode(String code) {
        this.code = code;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public Team setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }


    public String getCrestUrl() {
        return crestUrl;
    }

    public Team setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
        return this;
    }

    public static Comparator<Team> NAME_COMPARATOR = new Comparator<Team>() {
        @Override
        public int compare(Team t1, Team t2) {
            return t1.getName().compareTo(t2.getName());
        }
    };

}
