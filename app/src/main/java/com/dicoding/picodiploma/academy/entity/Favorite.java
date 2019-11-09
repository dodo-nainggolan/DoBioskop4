package com.dicoding.picodiploma.academy.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {

    public static final Creator<Favorite> CREATOR = new Creator<Favorite> () {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite ( in );
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
    private int id;
    private String namaFilm;
    private String rilisFilm;
    private String deskripsiFilm;
    private String gambarFilm;

    public Favorite(int id, String namaFilm, String rilisFilm, String deskripsiFilm, String gambarFilm) {
        this.id = id;
        this.namaFilm = namaFilm;
        this.rilisFilm = rilisFilm;
        this.deskripsiFilm = deskripsiFilm;
        this.gambarFilm = gambarFilm;
    }

    protected Favorite(Parcel in) {
        id = in.readInt ();
        namaFilm = in.readString ();
        rilisFilm = in.readString ();
        deskripsiFilm = in.readString ();
        gambarFilm = in.readString ();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dest.writeInt ( id );
        dest.writeString ( namaFilm );
        dest.writeString ( rilisFilm );
        dest.writeString ( deskripsiFilm );
        dest.writeString ( gambarFilm );
    }
}
