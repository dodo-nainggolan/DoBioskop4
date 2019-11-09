package com.dicoding.picodiploma.academy.adapter;

import com.dicoding.picodiploma.academy.entity.Movies;
import com.dicoding.picodiploma.academy.db.FavoriteHelper;

import java.util.ArrayList;

public class FavoriteAdapter {

    public final Movies movies = new Movies ();
    private ArrayList<Movies> listMovies = new ArrayList<> ();
    private String judulFilm, rilisFilm, deskripsiFilm, gambarFilm;
    private FavoriteHelper favoriteHelper;
}
