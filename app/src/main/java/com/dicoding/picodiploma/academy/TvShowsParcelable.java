package com.dicoding.picodiploma.academy;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShowsParcelable implements Parcelable {

    public static final Creator<TvShowsParcelable> CREATOR = new Creator<TvShowsParcelable> () {
        @Override
        public TvShowsParcelable createFromParcel(Parcel in) {
            return new TvShowsParcelable ( in );
        }

        @Override
        public TvShowsParcelable[] newArray(int size) {
            return new TvShowsParcelable[size];
        }
    };

    private String namaFilm;
    private String gambarFilm;
    private String deskripsiFilm;

    protected TvShowsParcelable(Parcel in) {
        namaFilm = in.readString ();
        gambarFilm = in.readString ();
        deskripsiFilm = in.readString ();
    }

    public TvShowsParcelable(JSONObject object) {
        try {
            String namaFilm = object.getString ( "original_name" );
            String gambarFilm = object.getString ( "poster_path" );
            String deskripsiFilm = object.getString ( "overview" );

            this.namaFilm = namaFilm;
            this.gambarFilm = gambarFilm;
            this.deskripsiFilm = deskripsiFilm;
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public TvShowsParcelable() {

    }

    public String getDeskripsiFilm() {
        return deskripsiFilm;
    }

    public void setDeskripsiFilm(String deskripsiFilm) {
        this.deskripsiFilm = deskripsiFilm;
    }

    public String getNamaFilm() {
        return namaFilm;
    }

    public void setNamaFilm(String namaFilm) {
        this.namaFilm = namaFilm;
    }

    public String getGambarFilm() {
        return gambarFilm;
    }

    public void setGambarFilm(String gambarFilm) {
        this.gambarFilm = gambarFilm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString ( namaFilm );
        dest.writeString ( gambarFilm );
        dest.writeString ( deskripsiFilm );
    }
}
