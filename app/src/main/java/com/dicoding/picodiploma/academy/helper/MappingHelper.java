package com.dicoding.picodiploma.academy.helper;

import android.database.Cursor;
import android.util.Log;

import com.dicoding.picodiploma.academy.entity.Favorite;
import com.dicoding.picodiploma.academy.db.DatabaseContract;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MappingHelper {

    public static ArrayList<Favorite> mapCursorToArrayList(Cursor moviesCursor) {
        ArrayList<Favorite> favList = new ArrayList<> ();

        while (moviesCursor.moveToNext ()) {
            int id = moviesCursor.getInt ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn._ID ) );
            String judulFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.JUDUL_FILM ) );
            String deskripsiFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.DESKRIPSI_FILM ) );
            String rilisFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.RILIS_FILM ) );
            String gambarFilm = moviesCursor.getString ( moviesCursor.getColumnIndexOrThrow ( DatabaseContract.MoviesColumn.GAMBAR_FILM ) );

            favList.add ( new Favorite ( id, judulFilm, rilisFilm, deskripsiFilm, gambarFilm ) );
            Log.e ( TAG, "mapCursorToArrayList: "+favList  );
        }
        return favList;
    }

}
