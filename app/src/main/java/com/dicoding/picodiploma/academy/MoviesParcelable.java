package com.dicoding.picodiploma.academy;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviesParcelable implements Parcelable {

    protected MoviesParcelable(Parcel in) {
        namaFilm = in.readString();
        rilisFilm = in.readString();
        deskripsiFilm = in.readString();
        fotoFilm = in.readInt();
    }

    public static final Creator<MoviesParcelable> CREATOR = new Creator<MoviesParcelable>() {
        @Override
        public MoviesParcelable createFromParcel(Parcel in) {
            return new MoviesParcelable(in);
        }

        @Override
        public MoviesParcelable[] newArray(int size) {
            return new MoviesParcelable[size];
        }
    };

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

    public int getFotoFilm() {
        return fotoFilm;
    }

    public void setFotoFilm(int fotoFilm) {
        this.fotoFilm = fotoFilm;
    }

    private String namaFilm;
    private String rilisFilm;
    private String deskripsiFilm;
    private int fotoFilm;



    public MoviesParcelable() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaFilm);
        dest.writeString(rilisFilm);
        dest.writeString(deskripsiFilm);
        dest.writeInt(fotoFilm);
    }
}
