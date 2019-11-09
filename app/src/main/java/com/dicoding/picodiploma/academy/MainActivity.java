package com.dicoding.picodiploma.academy;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dicoding.picodiploma.academy.db.FavoriteHelper;
import com.dicoding.picodiploma.academy.fragment.FavoriteFragment;
import com.dicoding.picodiploma.academy.fragment.MoviesFragment;
import com.dicoding.picodiploma.academy.fragment.TvShowsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FavoriteHelper favoriteHelper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener () {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            String title;

            switch (item.getItemId ()) {
                case R.id.navigation_home:

                    title = "MOVIES";
                    setTitle ( title );

                    fragment = new MoviesFragment ();
                    getSupportFragmentManager ().beginTransaction ()
                            .replace ( R.id.container_layout, fragment, fragment.getClass ().getSimpleName () )
                            .commit ();

                    return true;

                case R.id.navigation_dashboard:

                    title = "TV SHOWS";
                    setTitle ( title );
                    fragment = new TvShowsFragment ();
                    getSupportFragmentManager ().beginTransaction ()
                            .replace ( R.id.container_layout, fragment, fragment.getClass ().getSimpleName () )
                            .commit ();

                    return true;

                case R.id.favorite_dashboard:

                    title = "MY FAVORITE";
                    setTitle ( title );
                    fragment = new FavoriteFragment ();
                    getSupportFragmentManager ().beginTransaction ()
                            .replace ( R.id.container_layout, fragment, fragment.getClass ().getSimpleName () )
                            .commit ();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        BottomNavigationView navView = findViewById ( R.id.navigation );
        navView.setOnNavigationItemSelectedListener ( mOnNavigationItemSelectedListener );

        if (savedInstanceState == null) {
            navView.setSelectedItemId ( R.id.navigation_home );
        }

        favoriteHelper = FavoriteHelper.getInstance ( getApplicationContext () );
        favoriteHelper.open ();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.main_menu, menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId () == R.id.action_change_settings) {
            Intent mIntent = new Intent ( Settings.ACTION_LOCALE_SETTINGS );
            startActivity ( mIntent );
        }
        return super.onOptionsItemSelected ( item );
    }


}