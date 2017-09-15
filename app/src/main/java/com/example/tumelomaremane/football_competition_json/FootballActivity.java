package com.example.tumelomaremane.football_competition_json;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tumelomaremane.football_competition_json.api.Client;
import com.example.tumelomaremane.football_competition_json.fragments.LeagueTableFragment;
import com.example.tumelomaremane.football_competition_json.models.Team;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FootballActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_LOGO = 20;
    private static final String KEY_TEAM_ID = "teamId";
    private static final int DEFAULT_TEAM_ID = 57;

    private int mTeamId = DEFAULT_TEAM_ID;

    private Client mFootballData;

    private boolean mIsLogoShown = true;
    private int mMaxScrollSize;
    private Team team;

    @BindView(R.id.tam_tabs)
    TabLayout tabLayout;
    @BindView(R.id.team_viewpager)
    ViewPager viewPager;
    @BindView(R.id.team_appbar)
    AppBarLayout appbarLayout;
    @BindView(R.id.team_toolbar)
    Toolbar toolbar;
    @BindView(R.id.team_title)
    TextView mTitle;
    @BindView(R.id.team_subtitle)
    TextView mSubTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_tabs);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        mFootballData = Client.getClient();
        loadData();
    }

    public void loadData() {
        mFootballData.team(mTeamId).enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                team = response.body();
                showData();
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(FootballActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showData() {
        mTitle.setText(team.getName());
    }


    public static void start(Context c, int teamId) {
        Intent intent = new Intent(c, FootballActivity.class);
        intent.putExtra(KEY_TEAM_ID, teamId);
        c.startActivity(intent);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;


    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return LeagueTableFragment.newInstance(CompetitionActivity.LEAGUE_ID);
                case 1:
                    return LeagueTableFragment.newInstance(CompetitionActivity.LEAGUE_ID);
                case 2:
                    return LeagueTableFragment.newInstance(CompetitionActivity.LEAGUE_ID);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Fixtures";
                case 1:
                    return "League Table";
                case 2:
                    return "Teams";
            }
            return "";
        }
    }

}
