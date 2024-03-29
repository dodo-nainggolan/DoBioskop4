package com.dicoding.picodiploma.academy.adapter;

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

import com.dicoding.picodiploma.academy.FavoriteTvShowsDetailActivity;
import com.dicoding.picodiploma.academy.R;
import com.dicoding.picodiploma.academy.db.FavoriteTvShowsHelper;
import com.dicoding.picodiploma.academy.entity.FavoriteTvShows;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FavoriteTvShowsAdapter extends RecyclerView.Adapter<FavoriteTvShowsAdapter.CardViewViewHolder> {

    public final FavoriteTvShows fav = new FavoriteTvShows ();
    private ArrayList<FavoriteTvShows> listFav = new ArrayList<> ();
    private String judulFilm, rilisFilm, deskripsiFilm, gambarFilm;
    private int idFilm;
    private FavoriteTvShowsHelper favoriteTvShowsHelper;

    public FavoriteTvShowsAdapter() {

    }

    public ArrayList<FavoriteTvShows> getListNotes() {
        return listFav;
    }

    public void setListNotes(ArrayList<FavoriteTvShows> listNotes) {

        if (listNotes.size () > 0) {
            this.listFav.clear ();
        }
        this.listFav.addAll ( listNotes );

        notifyDataSetChanged ();
    }


    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.card_view_favorite_tvshows_layout, viewGroup, false );
        return new CardViewViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, final int position) {
        favoriteTvShowsHelper = FavoriteTvShowsHelper.getInstance ( null );
        String temp = listFav.get ( position ).getGambarFilm ();
        String url = "https://image.tmdb.org/t/p/w185" + temp;

        holder.tvJudul.setText ( listFav.get ( position ).getNamaFilm () );
        holder.tvRilis.setText ( listFav.get ( position ).getRilisFilm () );
        holder.tvDeskripsi.setText ( listFav.get ( position ).getDeskripsiFilm () );

        Picasso.get ().load ( url ).into ( holder.gambarFilm );

        holder.btnRemove.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                favoriteTvShowsHelper.deleteById ( String.valueOf ( listFav.get ( position ).getId () ) );
                Toast.makeText ( holder.itemView.getContext (), "Berhasil menghapus data", Toast.LENGTH_SHORT ).show ();
                listFav.remove ( position );
                notifyDataSetChanged ();
            }
        } );

        holder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( holder.itemView.getContext (), "Kamu memilih " +
                        listFav.get ( holder.getAdapterPosition () ).getNamaFilm (), Toast.LENGTH_SHORT ).show ();

                int cv = holder.getAdapterPosition ();
                ambildata ( cv );

                Intent intent = new Intent ( v.getContext (), FavoriteTvShowsDetailActivity.class );
                intent.putExtra ( "myData", fav );
                v.getContext ().startActivity ( intent );

            }
        } );
    }

    private void ambildata(int cv) {

        fav.setId ( listFav.get ( cv ).getId () );
        fav.setNamaFilm ( listFav.get ( cv ).getNamaFilm () );
        fav.setDeskripsiFilm ( listFav.get ( cv ).getDeskripsiFilm () );
        fav.setRilisFilm ( listFav.get ( cv ).getRilisFilm () );
        fav.setGambarFilm ( listFav.get ( cv ).getGambarFilm () );

        idFilm = fav.getId ();
        judulFilm = fav.getNamaFilm ();
        rilisFilm = fav.getRilisFilm ();
        deskripsiFilm = fav.getDeskripsiFilm ();
        gambarFilm = fav.getGambarFilm ();

    }

    @Override
    public int getItemCount() {
        return listFav.size ();
    }


    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        private ImageView gambarFilm;
        TextView tvJudul, tvRilis, tvDeskripsi;
        Button btnRemove;

        CardViewViewHolder(View itemView) {
            super ( itemView );
            gambarFilm = itemView.findViewById ( R.id.foto_film_fav );
            tvJudul = itemView.findViewById ( R.id.judul_film_fav );
            tvRilis = itemView.findViewById ( R.id.rilis_film_fav );
            tvDeskripsi = itemView.findViewById ( R.id.deskripsi_film_fav );
            btnRemove = itemView.findViewById ( R.id.btn_remove_fav );
        }
    }
}
