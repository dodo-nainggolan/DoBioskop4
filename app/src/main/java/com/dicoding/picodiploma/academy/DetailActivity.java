package com.dicoding.picodiploma.academy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail Film");

        TextView tvJudul = findViewById(R.id.judul_film);
        TextView tvDeskripsi = findViewById(R.id.deskripsi_film);
        TextView tvRilis = findViewById(R.id.rilis_film);
        ImageView tvFotoDetail = findViewById(R.id.foto_detail);

        MoviesParcelable moviesParcelable = getIntent().getParcelableExtra("myData");

        if (moviesParcelable != null) {
            tvJudul.setText(moviesParcelable.getNamaFilm());
            tvDeskripsi.setText(moviesParcelable.getDeskripsiFilm());
            tvRilis.setText(moviesParcelable.getRilisFilm());
            tvFotoDetail.setImageResource(moviesParcelable.getFotoFilm());
        }
    }
}
