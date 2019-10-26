package com.dicoding.picodiploma.academy;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private ArrayList<MoviesParcelable> list = new ArrayList<>();
    private TypedArray dataPhoto;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new MoviesAdapter(getListMovies()));
        // Inflate the layout for this fragment
        
        return rv;
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
}
