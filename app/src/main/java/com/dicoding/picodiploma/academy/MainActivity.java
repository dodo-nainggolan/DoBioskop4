package com.dicoding.picodiploma.academy;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.picodiploma.academy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //    private TextView mTextMessage;
    private RecyclerView rvMovies;
    private ArrayList<MoviesParcelable> list = new ArrayList<>();
    private TypedArray dataPhoto;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showRecyclerCardView();
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
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
//        mTextMessage = findViewById(R.id.container_layout);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        rvMovies = findViewById(R.id.container_layout);
        rvMovies.setHasFixedSize(true);
//
        list.addAll(getListMovies());
    }


    public ArrayList<MoviesParcelable> getListMovies() {
        String[] dataName = getResources().getStringArray(R.array.nama_film);
        String[] dataDescription = getResources().getStringArray(R.array.deskripsi_film);
        String[] dataRilis = getResources().getStringArray(R.array.rilis_film);
        dataPhoto = getResources().obtainTypedArray(R.array.data_foto);

        ArrayList<MoviesParcelable> listMovies = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            MoviesParcelable movies = new MoviesParcelable();
            movies.setNamaFilm(dataName[i]);
            movies.setDeskripsiFilm(dataDescription[i]);
            movies.setRilisFilm(dataRilis[i]);
            movies.setFotoFilm(dataPhoto.getResourceId(i, -1));
            listMovies.add(movies);
        }
        return listMovies;
    }

    private void showRecyclerCardView() {
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        MoviesAdapter cardViewHeroAdapter = new MoviesAdapter(list);
        rvMovies.setAdapter(cardViewHeroAdapter);
    }

}