package com.dicoding.picodiploma.academy;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.picodiploma.academy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new MoviesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;

                case R.id.navigation_dashboard:

                    fragment = new TvShowsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.navigation);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.navigation_home);
        }
//
    }

//
}