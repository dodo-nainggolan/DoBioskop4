package com.dicoding.picodiploma.academy;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements View.OnClickListener,LoaderManager.LoaderCallbacks<ArrayList<MoviesParcelable>> {

    MoviesAdapter adapter;
    private ArrayList<MoviesParcelable> list = new ArrayList<> ();
    private RecyclerView rv;
    private ProgressBar progressBar;


    public MoviesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.fragment_movies, container, false );
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        adapter = new MoviesAdapter ();
        adapter.notifyDataSetChanged ();

        rv = view.findViewById ( R.id.card_view_list_item );
        progressBar = view.findViewById ( R.id.progressBar );

        rv.setLayoutManager ( new LinearLayoutManager ( getContext () ) );
        rv.setAdapter ( adapter );

        Bundle bundle = new Bundle ();
        getLoaderManager ().initLoader ( 0, bundle, this );
        showLoading ( true );

    }

    @Override
    public Loader<ArrayList<MoviesParcelable>> onCreateLoader(int id, Bundle args) {
        return new MoviesAsyncTaskLoader ( getActivity (), "" );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MoviesParcelable>> loader, ArrayList<MoviesParcelable> data) {

        adapter.setData ( data );
        showLoading ( false );
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MoviesParcelable>> loader) {

//TODO optimize dirty trick
//                adapter.setData ( null );
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility ( View.VISIBLE );
        } else {
            progressBar.setVisibility ( View.GONE );
        }
    }

    @Override
    public void onClick(View v) {

    }
}

