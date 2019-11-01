package com.dicoding.picodiploma.academy;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TvShowsFragment extends Fragment {
    private TypedArray dataPhoto;

    public TvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.grid_view_list_item);

        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        TvShowsAdapter tvShowsAdapter = new TvShowsAdapter(getListMovies());

        rv.setAdapter(tvShowsAdapter);
    }

    public ArrayList<MoviesParcelable> getListMovies() {
        String[] dataName = getResources().getStringArray(R.array.nama_film);
        String[] dataRilis = getResources().getStringArray(R.array.rilis_film);
        String[] deskripsiFilm = getResources().getStringArray(R.array.deskripsi_film);
        dataPhoto = getResources().obtainTypedArray(R.array.data_foto);

        ArrayList<MoviesParcelable> listMovies = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            MoviesParcelable movies = new MoviesParcelable();
            movies.setNamaFilm(dataName[i]);
            movies.setRilisFilm(dataRilis[i]);
            movies.setDeskripsiFilm(deskripsiFilm[i]);
            movies.setFotoFilm(dataPhoto.getResourceId(i, -1));
            listMovies.add(movies);
        }
        return listMovies;
    }
}


