package com.dicoding.picodiploma.academy.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NAME = "domovies";

    static final class MoviesColumn implements BaseColumns {

        static String JUDUL_FILM = "judulFilm";
        static String DESKRIPSI_FILM = "deksripsiFilm";
        static String RILIS_FILM = "rilisFilm";
        static String GAMBAR_FILM = "gambarFIlm";

    }
}
