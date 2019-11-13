package com.dicoding.picodiploma.academy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.dicoding.picodiploma.academy.adapter.MoviesAdapter;
import com.dicoding.picodiploma.academy.entity.Movies;
import com.dicoding.picodiploma.academy.fragment.MoviesAsyncTaskLoader;
import com.dicoding.picodiploma.academy.fragment.MoviesFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<Bitmap> mWidgetItems = new ArrayList<> ();
    private final Context mContext;
//    MoviesAdapter adapter;
//    String movies;
//    private ArrayList<Movies> listMovies = new ArrayList ();

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }


    @Override
    public void onCreate() {



    }

    @Override
    public void onDataSetChanged() {

//        adapter = new MoviesAdapter ();
//        adapter.notifyDataSetChanged ();
//        adapter.setData ( listMovies );
//
//        Log.e ( TAG, "onDataSetChanged: "+listMovies.size ()  );
//        Log.e ( TAG, "onDataSetChanged: "+adapter.getItemCount ()  );

        mWidgetItems.add ( BitmapFactory.decodeResource ( mContext.getResources (), R.drawable.dicoding_logo ) );
        mWidgetItems.add ( BitmapFactory.decodeResource ( mContext.getResources (), R.drawable.dicoding_logo ) );
        mWidgetItems.add ( BitmapFactory.decodeResource ( mContext.getResources (), R.drawable.dicoding_logo ) );
        mWidgetItems.add ( BitmapFactory.decodeResource ( mContext.getResources (), R.drawable.dicoding_logo ) );
        mWidgetItems.add ( BitmapFactory.decodeResource ( mContext.getResources (), R.drawable.dicoding_logo ) );

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.e ( TAG, "HALO SEMUA"  );
        return mWidgetItems.size ();

    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews rv = new RemoteViews ( mContext.getPackageName (), R.layout.widget_item );
        rv.setImageViewBitmap ( R.id.imageView, mWidgetItems.get ( position ) );

        Bundle extras = new Bundle ();
        extras.putInt ( ImageBannerWidget.EXTRA_ITEM, position );

        Intent fillInIntent = new Intent ();

        fillInIntent.putExtras ( extras );
        rv.setOnClickFillInIntent ( R.id.imageView, fillInIntent );


        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
