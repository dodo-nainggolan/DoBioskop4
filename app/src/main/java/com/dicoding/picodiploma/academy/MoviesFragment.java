package com.dicoding.picodiploma.academy;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private ArrayList<MoviesParcelable> list = new ArrayList<>();
    private TypedArray dataPhoto;
    private String[] dataName;
    private String[] dataDescription;
    private String[] dataRilis;
    private RecyclerView rv;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.card_view_list_item);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new MoviesAdapter(getListMovies()));
    }

    public ArrayList<MoviesParcelable> getListMovies() {
        dataName = getResources().getStringArray(R.array.nama_film);
        dataDescription = getResources().getStringArray(R.array.deskripsi_film);
        dataRilis = getResources().getStringArray(R.array.rilis_film);
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
