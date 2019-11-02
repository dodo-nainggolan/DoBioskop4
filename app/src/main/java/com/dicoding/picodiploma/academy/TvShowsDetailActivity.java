package com.dicoding.picodiploma.academy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TvShowsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_movie_detail );
        setTitle ( "Detail Film" );

        TextView tvJudul = findViewById ( R.id.judul_film );
        TextView tvDeskripsi = findViewById ( R.id.deskripsi_film );
        TextView tvRilis = findViewById ( R.id.rilis_film );
        ImageView tvFotoDetail = findViewById ( R.id.foto_detail );

        TvShowsParcelable tvShowsParcelable = getIntent ().getParcelableExtra ( "myData" );


        if (tvShowsParcelable != null) {

            String url = "https://image.tmdb.org/t/p/w500" + tvShowsParcelable.getGambarFilm ();

            tvJudul.setText ( tvShowsParcelable.getNamaFilm () );
            tvDeskripsi.setText ( tvShowsParcelable.getDeskripsiFilm () );
            Picasso.get ().load ( url ).into ( tvFotoDetail );
        }
    }
}
