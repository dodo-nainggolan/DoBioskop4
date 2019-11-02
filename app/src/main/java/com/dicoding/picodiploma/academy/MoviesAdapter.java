package com.dicoding.picodiploma.academy;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CardViewViewHolder> {

    private ArrayList<MoviesParcelable> listMovies = new ArrayList<> ();

    public MoviesAdapter() {

    }

    public void setData(ArrayList<MoviesParcelable> items) {
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


        CardViewViewHolder.tvJudul.setText ( listMovies.get ( position ).getNamaFilm () );
        CardViewViewHolder.tvRilis.setText ( listMovies.get ( position ).getRilisFilm () );
        CardViewViewHolder.tvDeskripsi.setText ( listMovies.get ( position ).getDeskripsiFilm () );

        Picasso.get ().load ( url ).into ( CardViewViewHolder.Gambar );

        CardViewViewHolder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( CardViewViewHolder.itemView.getContext (), "Kamu memilih " +
                        listMovies.get ( CardViewViewHolder.getAdapterPosition () ).getNamaFilm (), Toast.LENGTH_SHORT ).show ();

                MoviesParcelable moviesParcelable = new MoviesParcelable ();

                moviesParcelable.setNamaFilm ( listMovies.get ( CardViewViewHolder.getAdapterPosition () ).getNamaFilm () );
                moviesParcelable.setDeskripsiFilm ( listMovies.get ( CardViewViewHolder.getAdapterPosition () ).getDeskripsiFilm () );
                moviesParcelable.setRilisFilm ( listMovies.get ( CardViewViewHolder.getAdapterPosition () ).getRilisFilm () );
                moviesParcelable.setGambarFilm (listMovies.get ( CardViewViewHolder.getAdapterPosition () ).getGambarFilm ());

                Intent intent = new Intent ( v.getContext (), MoviesDetailActivity.class );
                intent.putExtra ( "myData", moviesParcelable );
                v.getContext ().startActivity ( intent );

            }
        } );
    }

    @Override
    public int getItemCount() {
        return listMovies.size ();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        public ImageView Gambar;
        TextView tvJudul, tvRilis, tvDeskripsi;

        CardViewViewHolder(View itemView) {
            super ( itemView );
            Gambar = itemView.findViewById ( R.id.foto_film );
            tvJudul = itemView.findViewById ( R.id.judul_film );
            tvRilis = itemView.findViewById ( R.id.rilis_film );
            tvDeskripsi = itemView.findViewById ( R.id.deskripsi_film );
        }
    }

}
