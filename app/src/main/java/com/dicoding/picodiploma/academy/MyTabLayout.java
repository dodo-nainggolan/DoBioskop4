package com.dicoding.picodiploma.academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.picodiploma.academy.adapter.SectionsPagerAdapter;
import com.dicoding.picodiploma.academy.db.FavoriteHelper;
import com.dicoding.picodiploma.academy.fragment.MoviesFragment;
import com.dicoding.picodiploma.academy.fragment.TvShowsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MyTabLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_my_tab_layout );


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter ( this, getSupportFragmentManager () );
        ViewPager viewPager = findViewById ( R.id.view_pager );
        viewPager.setAdapter ( sectionsPagerAdapter );
        TabLayout tabs = findViewById ( R.id.tabs );
        tabs.setupWithViewPager ( viewPager );

        getSupportActionBar ().setElevation ( 0 );
    }

}
