package com.dicoding.picodiploma.academy.helper;

import android.database.Cursor;

import com.dicoding.picodiploma.academy.entity.Movies;
import com.dicoding.picodiploma.academy.db.DatabaseContract;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<Movies> mapCursorToArrayList(Cursor moviesCursor) {
        ArrayList<Movies> moviesList = new ArrayList<> ();

        while (moviesCursor.moveToNext ()) {
            int id = moviesCursor.getInt ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn._ID ) );
            String judulFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.JUDUL_FILM ) );
            String deskripsiFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.DESKRIPSI_FILM ) );
            String rilisFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.RILIS_FILM ) );
            String gambarFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.GAMBAR_FILM ) );

            moviesList.add ( new Movies ( id, judulFilm, rilisFilm, deskripsiFilm, gambarFilm ) );
        }
        return moviesList;
    }

}
