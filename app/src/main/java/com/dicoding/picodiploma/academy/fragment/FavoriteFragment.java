package com.dicoding.picodiploma.academy.fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dicoding.picodiploma.academy.R;
import com.dicoding.picodiploma.academy.adapter.FavoriteAdapter;
import com.dicoding.picodiploma.academy.db.FavoriteHelper;
import com.dicoding.picodiploma.academy.entity.Favorite;
import com.dicoding.picodiploma.academy.helper.MappingHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

interface LoadNotesCallback {
    void preExecute();

    void postExecute(ArrayList<Favorite> favorites);
}

public class FavoriteFragment extends Fragment implements LoadNotesCallback {

    private static final String EXTRA_STATE = "EXTRA_STATE";
    private FavoriteAdapter adapter;
    private RecyclerView rv;
    private ProgressBar progressBar;
    private FavoriteHelper favoriteHelper;
    private TextView namaFilm;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.fragment_favorite, container, false );
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        adapter = new FavoriteAdapter ();
        adapter.notifyDataSetChanged ();

        rv = view.findViewById ( R.id.card_view_list_item_fav );
        progressBar = view.findViewById ( R.id.progressBar );

        rv.setLayoutManager ( new LinearLayoutManager ( getContext () ) );
        rv.setAdapter ( adapter );

        favoriteHelper = FavoriteHelper.getInstance ( getContext () );
        favoriteHelper.open ();

        new LoadNotesAsync ( favoriteHelper, this ).execute ();

    }

    @Override
    public void preExecute() {
    }

    @Override
    public void postExecute(ArrayList<Favorite> favorite) {
        Log.e ( "MainActivity", "postExecute: "+favorite  );
        adapter.setListNotes ( favorite );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState ( outState );
        outState.putParcelableArrayList ( EXTRA_STATE, adapter.getListNotes () );
    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<Favorite>> {

        private final WeakReference<FavoriteHelper> weakFavHelper;
        private final WeakReference<LoadNotesCallback> weakCallback;

        private LoadNotesAsync(FavoriteHelper noteHelper, LoadNotesCallback callback) {
            weakFavHelper = new WeakReference<> ( noteHelper );
            weakCallback = new WeakReference<> ( callback );
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute ();
            weakCallback.get ().preExecute ();
        }

        @Override
        protected ArrayList<Favorite> doInBackground(Void... voids) {
            Cursor dataCursor = weakFavHelper.get ().queryAll ();
            Log.e ( TAG, "doInBackground: " + dataCursor );
            return MappingHelper.mapCursorToArrayList ( dataCursor );
        }

        @Override
        protected void onPostExecute(ArrayList<Favorite> favorites) {
            super.onPostExecute ( favorites );

            weakCallback.get ().postExecute ( favorites );

        }
    }
}
