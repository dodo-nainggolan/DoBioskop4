package com.dicoding.picodiploma.academy;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CardViewViewHolder> {

    private ArrayList<MoviesParcelable> listMovies;
    private TypedArray dataFoto;


    public MoviesAdapter(ArrayList<MoviesParcelable> list) {
        this.listMovies = list;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.card_view_movies_layout, viewGroup, false );

        return new CardViewViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final MoviesParcelable movies = listMovies.get ( position );

        Glide.with ( holder.itemView.getContext () )
                .load ( movies.getFotoFilm () )
                .apply ( new RequestOptions ().override ( 350, 550 ) )
                .into ( holder.imgPhoto );

        holder.tvJudul.setText ( movies.getNamaFilm () );
        holder.tvRilis.setText ( movies.getRilisFilm () );
        holder.tvDeskripsi.setText ( movies.getDeskripsiFilm () );

        holder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( holder.itemView.getContext (), "Kamu memilih " +
                        listMovies.get ( holder.getAdapterPosition () ).getNamaFilm (), Toast.LENGTH_SHORT ).show ();
                MoviesParcelable moviesParcelable = new MoviesParcelable ();
                moviesParcelable.setNamaFilm ( listMovies.get ( holder.getAdapterPosition () ).getNamaFilm () );
                moviesParcelable.setDeskripsiFilm ( listMovies.get ( holder.getAdapterPosition () ).getDeskripsiFilm () );
                moviesParcelable.setRilisFilm ( listMovies.get ( holder.getAdapterPosition () ).getRilisFilm () );
                moviesParcelable.setFotoFilm (listMovies.get ( holder.getAdapterPosition () ).getFotoFilm ());

                Intent intent = new Intent ( v.getContext (), DetailActivity.class );
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
        ImageView imgPhoto;
        TextView tvJudul, tvRilis, tvDeskripsi;

        CardViewViewHolder(View itemView) {
            super ( itemView );
            imgPhoto = itemView.findViewById ( R.id.foto_film );
            tvJudul = itemView.findViewById ( R.id.judul_film );
            tvRilis = itemView.findViewById ( R.id.rilis_film );
            tvDeskripsi = itemView.findViewById ( R.id.deskripsi_film );
        }
    }

}
