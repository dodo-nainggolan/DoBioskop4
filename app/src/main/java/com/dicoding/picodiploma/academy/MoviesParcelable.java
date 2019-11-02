package com.dicoding.picodiploma.academy;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class MoviesParcelable implements Parcelable {

    public static final Creator<MoviesParcelable> CREATOR = new Creator<MoviesParcelable> () {
        @Override
        public MoviesParcelable createFromParcel(Parcel in) {
            return new MoviesParcelable ( in );
        }

        @Override
        public MoviesParcelable[] newArray(int size) {
            return new MoviesParcelable[size];
        }
    };

    private String namaFilm;
    private String rilisFilm;
    private String deskripsiFilm;
    private String gambarFilm;

    protected MoviesParcelable(Parcel in) {
        namaFilm = in.readString ();
        rilisFilm = in.readString ();
        deskripsiFilm = in.readString ();
        gambarFilm = in.readString ();
    }

    public MoviesParcelable(JSONObject object) {
        try {
            String namaFilm = object.getString ( "title" );
            String rilisFilm = object.getString ( "release_date" );
            String deskripsiFilm = object.getString ( "overview" );
            String gambarFilm = object.getString ( "poster_path" );

            this.namaFilm = namaFilm;
            this.rilisFilm = rilisFilm;
            this.deskripsiFilm = deskripsiFilm;
            this.gambarFilm = gambarFilm;
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public MoviesParcelable() {

    }

    public String getNamaFilm() {
        return namaFilm;
    }

    public void setNamaFilm(String namaFilm) {
        this.namaFilm = namaFilm;
    }

    public String getRilisFilm() {
        return rilisFilm;
    }

    public void setRilisFilm(String rilisFilm) {
        this.rilisFilm = rilisFilm;
    }

    public String getDeskripsiFilm() {
        return deskripsiFilm;
    }

    public void setDeskripsiFilm(String deskripsiFilm) {
        this.deskripsiFilm = deskripsiFilm;
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
        dest.writeString ( rilisFilm );
        dest.writeString ( deskripsiFilm );
        dest.writeString ( gambarFilm );
    }


}
