package com.dicoding.picodiploma.academy.db;

import android.provider.BaseColumns;

public class DatabaseContractTvShows {

    public static final String TABLE_NAME = "domoviestv";

    public final class MoviesColumn implements BaseColumns {

        public static final String JUDUL_FILM = "judulFilm";
        public static final String DESKRIPSI_FILM = "deksripsiFilm";
        public static final String RILIS_FILM = "rilisFilm";
        public static final String GAMBAR_FILM = "gambarFIlm";

    }
}
