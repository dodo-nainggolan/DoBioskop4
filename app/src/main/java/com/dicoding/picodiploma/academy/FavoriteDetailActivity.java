package com.dicoding.picodiploma.academy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.picodiploma.academy.entity.Favorite;
import com.dicoding.picodiploma.academy.entity.Movies;
import com.squareup.picasso.Picasso;

public class FavoriteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_movie_detail );
        setTitle ( "Detail Film" );

        TextView tvJudul = findViewById ( R.id.judul_film );
        TextView tvDeskripsi = findViewById ( R.id.deskripsi_film );
        TextView tvRilis = findViewById ( R.id.rilis_film );
        ImageView tvFotoDetail = findViewById ( R.id.foto_detail );

        Favorite favorite = getIntent ().getParcelableExtra ( "myData" );

        if (favorite != null) {

            String url = "https://image.tmdb.org/t/p/w500" + favorite.getGambarFilm ();

            tvJudul.setText ( favorite.getNamaFilm () );
            tvDeskripsi.setText ( favorite.getDeskripsiFilm () );
            tvRilis.setText ( favorite.getRilisFilm () );
            Picasso.get ().load ( url ).into ( tvFotoDetail );
        }
    }
}
