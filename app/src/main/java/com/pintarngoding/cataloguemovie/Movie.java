package com.pintarngoding.cataloguemovie;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.Date;

public class Movie implements Parcelable {
    private String title;
    private String overview;
    private String release_date;
    private String rating;
    private String poster;
    private String backdrop;

    public Movie() {

    }

    public Movie(JSONObject jsonObject) {
        try {
            String title = jsonObject.getString("title");
            String overview = jsonObject.getString("overview");
            String release_date = jsonObject.getString("release_date");
            String rating = jsonObject.getString("vote_average");
            String poster = jsonObject.getString("poster_path");
            String backdrop = jsonObject.getString("backdrop_path");

            this.title = title;
            this.overview = overview;
            this.release_date = release_date;
            this.rating = rating;
            String urlposter = "http://image.tmdb.org/t/p/w185"+poster;
            this.poster = urlposter;
            String urlbackdrop = "http://image.tmdb.org/t/p/w500"+backdrop;
            this.backdrop = urlbackdrop;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.rating);
        dest.writeString(this.poster);
        dest.writeString(this.backdrop);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.rating = in.readString();
        this.poster = in.readString();
        this.backdrop = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
