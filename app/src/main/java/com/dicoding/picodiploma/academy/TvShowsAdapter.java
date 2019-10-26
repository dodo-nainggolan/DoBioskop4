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

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.GridViewHolder> {
    private ArrayList<MoviesParcelable> listMovies;

    public TvShowsAdapter(ArrayList<MoviesParcelable> list) {
        this.listMovies = list;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_view_tv_shows, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsAdapter.GridViewHolder holder, int position) {
        MoviesParcelable movies = listMovies.get(position);

        Glide.with(holder.itemView.getContext())
                .load(listMovies.get(position).getFotoFilm())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.tvJudul.setText(movies.getNamaFilm());
        holder.tvRilis.setText(movies.getRilisFilm());
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvRilis;

        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.foto_film);
            tvJudul = itemView.findViewById(R.id.judul_film);
            tvRilis = itemView.findViewById(R.id.rilis_film);
        }
    }
}
