package com.dicoding.picodiploma.academy;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowsAsyncTaskLoader extends AsyncTaskLoader<ArrayList<TvShowsParcelable>> {

    private static final String API_KEY = "3a92c0f2b44f88d27ffe2b2de5466cc6";
    private ArrayList<TvShowsParcelable> mData;
    private boolean mHasResult = false;
    private String tvshow;

    TvShowsAsyncTaskLoader(final Context context, String tvshow) {
        super ( context );
        onContentChanged ();
        this.tvshow = tvshow;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged ())
            forceLoad ();
        else if (mHasResult)
            deliverResult ( mData );
    }

    @Override
    public void deliverResult(final ArrayList<TvShowsParcelable> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult ( data );
    }

    @Override
    protected void onReset() {
        super.onReset ();
        onStopLoading ();
        if (mHasResult) {
            mData = null;
            mHasResult = false;
        }
    }

    @Override
    public ArrayList<TvShowsParcelable> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient ();
        final ArrayList<TvShowsParcelable> tvshowsItemses = new ArrayList<> ();

        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY;

        client.get ( url, new AsyncHttpResponseHandler () {
            @Override
            public void onStart() {
                super.onStart ();
                setUseSynchronousMode ( true );
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String ( responseBody );
                    JSONObject responseObject = new JSONObject ( result );
                    JSONArray list = responseObject.getJSONArray ( "results" );

                    for (int i = 0; i < list.length (); i++) {
                        JSONObject tvshows = list.getJSONObject ( i );
                        TvShowsParcelable tvshowsItems = new TvShowsParcelable ( tvshows );
                        tvshowsItemses.add ( tvshowsItems );

                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Jika response gagal maka , do nothing
            }
        } );
        return tvshowsItemses;
    }
}
