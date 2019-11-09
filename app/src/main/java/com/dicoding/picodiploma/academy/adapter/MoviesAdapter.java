package com.dicoding.picodiploma.academy.adapter;

import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.picodiploma.academy.MoviesDetailActivity;
import com.dicoding.picodiploma.academy.entity.Movies;
import com.dicoding.picodiploma.academy.R;
import com.dicoding.picodiploma.academy.db.FavoriteHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.dicoding.picodiploma.academy.db.DatabaseContract.MoviesColumn.DESKRIPSI_FILM;
import static com.dicoding.picodiploma.academy.db.DatabaseContract.MoviesColumn.GAMBAR_FILM;
import static com.dicoding.picodiploma.academy.db.DatabaseContract.MoviesColumn.JUDUL_FILM;
import static com.dicoding.picodiploma.academy.db.DatabaseContract.MoviesColumn.RILIS_FILM;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CardViewViewHolder> {

    public final Movies movies = new Movies ();
    private ArrayList<Movies> listMovies = new ArrayList<> ();
    private String judulFilm, rilisFilm, deskripsiFilm, gambarFilm;
    private FavoriteHelper favoriteHelper;

    public MoviesAdapter() {

    }

    public void setData(ArrayList<Movies> items) {
        listMovies.clear ();
        listMovies.addAll ( items );
        notifyDataSetChanged ();
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.card_view_movies_layout, viewGroup, false );
        return new CardViewViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder CardViewViewHolder, int position) {

        String temp = listMovies.get ( position ).getGambarFilm ();
        String url = "https://image.tmdb.org/t/p/w185" + temp;

        favoriteHelper = FavoriteHelper.getInstance ( null );

        CardViewViewHolder.tvJudul.setText ( listMovies.get ( position ).getNamaFilm () );
        CardViewViewHolder.tvRilis.setText ( listMovies.get ( position ).getRilisFilm () );
        CardViewViewHolder.tvDeskripsi.setText ( listMovies.get ( position ).getDeskripsiFilm () );

        Picasso.get ().load ( url ).into ( CardViewViewHolder.Gambar );

        CardViewViewHolder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( CardViewViewHolder.itemView.getContext (), "Kamu memilih " +
                        listMovies.get ( CardViewViewHolder.getAdapterPosition () ).getNamaFilm (), Toast.LENGTH_SHORT ).show ();

                int cv = CardViewViewHolder.getPosition ();
                ambildata ( cv );

                Intent intent = new Intent ( v.getContext (), MoviesDetailActivity.class );
                intent.putExtra ( "myData", movies );
                v.getContext ().startActivity ( intent );

            }
        } );

        CardViewViewHolder.btnFav.setOnClickListener ( new View.OnClickListener () {

            @Override
            public void onClick(View v) {
                Toast.makeText ( CardViewViewHolder.btnFav.getContext (), "tombol berhasil ditekan", Toast.LENGTH_SHORT ).show ();
                int cv = CardViewViewHolder.getPosition ();
                ambildata ( cv );

                ContentValues values = new ContentValues ();
                values.put ( JUDUL_FILM, judulFilm );
                values.put ( DESKRIPSI_FILM, deskripsiFilm );
                values.put ( RILIS_FILM, rilisFilm );
                values.put ( GAMBAR_FILM, gambarFilm );

                long result = favoriteHelper.insert ( values );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return listMovies.size ();
    }

    public void ambildata(int cv) {

        movies.setNamaFilm ( listMovies.get ( cv ).getNamaFilm () );
        movies.setDeskripsiFilm ( listMovies.get ( cv ).getDeskripsiFilm () );
        movies.setRilisFilm ( listMovies.get ( cv ).getRilisFilm () );
        movies.setGambarFilm ( listMovies.get ( cv ).getGambarFilm () );

        judulFilm = movies.getNamaFilm ();
        rilisFilm = movies.getRilisFilm ();
        deskripsiFilm = movies.getDeskripsiFilm ();
        gambarFilm = movies.getGambarFilm ();

//        Log.e ( TAG, "ambildata: "+judulFilm  );
//        Log.e ( TAG, "ambildata: "+rilisFilm  );
//        Log.e ( TAG, "ambildata: "+deskripsiFilm  );
//        Log.e ( TAG, "ambildata: "+gambarFilm  );

    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        public ImageView Gambar;
        public Button btnFav;
        TextView tvJudul, tvRilis, tvDeskripsi;

        CardViewViewHolder(View itemView) {
            super ( itemView );
            Gambar = itemView.findViewById ( R.id.foto_film );
            tvJudul = itemView.findViewById ( R.id.judul_film );
            tvRilis = itemView.findViewById ( R.id.rilis_film );
            tvDeskripsi = itemView.findViewById ( R.id.deskripsi_film );
            btnFav = itemView.findViewById ( R.id.btn_fav );
        }
    }

}
