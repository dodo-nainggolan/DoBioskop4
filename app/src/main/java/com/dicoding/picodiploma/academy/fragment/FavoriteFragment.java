package com.dicoding.picodiploma.academy.fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.picodiploma.academy.R;
import com.dicoding.picodiploma.academy.adapter.MoviesAdapter;
import com.dicoding.picodiploma.academy.db.FavoriteHelper;
import com.dicoding.picodiploma.academy.entity.Movies;
import com.dicoding.picodiploma.academy.helper.MappingHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

interface LoadNotesCallback {
    void preExecute();

    void postExecute(ArrayList<Movies> notes);
}

public class FavoriteFragment extends Fragment implements LoadNotesCallback {
    private final ArrayList<Movies> listMovies = new ArrayList<> ();

    MoviesAdapter adapter;
    private RecyclerView rv;
    private ProgressBar progressBar;
    private FavoriteHelper favoriteHelper;

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

        adapter = new MoviesAdapter ();
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
    public void postExecute(ArrayList<Movies> notes) {

    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<Movies>> {

        private final WeakReference<FavoriteHelper> weakNoteHelper;
        private final WeakReference<LoadNotesCallback> weakCallback;

        private LoadNotesAsync(FavoriteHelper noteHelper, LoadNotesCallback callback) {
            weakNoteHelper = new WeakReference<> ( noteHelper );
            weakCallback = new WeakReference<> ( callback );
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute ();
            weakCallback.get ().preExecute ();
        }

        @Override
        protected ArrayList<Movies> doInBackground(Void... voids) {
            Cursor dataCursor = weakNoteHelper.get ().queryAll ();
            return MappingHelper.mapCursorToArrayList ( dataCursor );
        }

        @Override
        protected void onPostExecute(ArrayList<Movies> movies) {
            super.onPostExecute ( movies );

            weakCallback.get ().postExecute ( movies );

        }
    }
}
