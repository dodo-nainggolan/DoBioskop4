package com.dicoding.picodiploma.academy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CardViewViewHolder> {

    private ArrayList<MoviesParcelable> listMovies;

    public MoviesAdapter(ArrayList<MoviesParcelable> list) {
        this.listMovies = list;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_movies_layout, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        MoviesParcelable movies = listMovies.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movies.getFotoFilm())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.tvJudul.setText(movies.getNamaFilm());
        holder.tvRilis.setText(movies.getRilisFilm());
        holder.tvDeskripsi.setText(movies.getDeskripsiFilm());
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvRilis, tvDeskripsi;

        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.foto_film);
            tvJudul = itemView.findViewById(R.id.judul_film);
            tvRilis = itemView.findViewById(R.id.rilis_film);
            tvDeskripsi = itemView.findViewById(R.id.deskripsi_film);
        }
    }
}
