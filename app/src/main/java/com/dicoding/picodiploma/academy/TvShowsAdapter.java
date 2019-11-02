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

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.GridViewHolder> {

    private ArrayList<TvShowsParcelable> listTvShows = new ArrayList<> ();

    public TvShowsAdapter() {

    }

    public void setData(ArrayList<TvShowsParcelable> items) {
        listTvShows.clear ();
        listTvShows.addAll ( items );
        notifyDataSetChanged ();
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.grid_view_tv_shows, viewGroup, false );
        return new GridViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder GridViewHolder, int position) {
        String temp = listTvShows.get ( position ).getGambarFilm ();

        String url = "https://image.tmdb.org/t/p/w185" + temp;

        GridViewHolder.tvJudul.setText ( listTvShows.get ( position ).getNamaFilm () );
        Picasso.get ().load ( url ).into ( GridViewHolder.Gambar );

        GridViewHolder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( GridViewHolder.itemView.getContext (), "Kamu memilih " +
                        listTvShows.get ( GridViewHolder.getAdapterPosition () ).getNamaFilm (), Toast.LENGTH_SHORT ).show ();

                TvShowsParcelable tvShowsParcelable = new TvShowsParcelable ();

                tvShowsParcelable.setNamaFilm ( listTvShows.get ( GridViewHolder.getAdapterPosition () ).getNamaFilm () );
                tvShowsParcelable.setDeskripsiFilm ( listTvShows.get ( GridViewHolder.getAdapterPosition () ).getDeskripsiFilm () );
                tvShowsParcelable.setGambarFilm ( listTvShows.get ( GridViewHolder.getAdapterPosition () ).getGambarFilm () );

                Intent intent = new Intent ( v.getContext (), TvShowsDetailActivity.class );
                intent.putExtra ( "myData", tvShowsParcelable );
                v.getContext ().startActivity ( intent );

            }
        } );

    }

    @Override
    public int getItemCount() {
        return listTvShows.size ();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView Gambar;
        TextView tvJudul;

        GridViewHolder(View itemView) {
            super ( itemView );
            Gambar = itemView.findViewById ( R.id.foto_film );
            tvJudul = itemView.findViewById ( R.id.judul_film );
        }
    }
}
