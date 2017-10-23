package app.com.example.songoku.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by songoku on 3/10/17.
 */

public class MovieDetail implements Parcelable {
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";


     String title;

    @Expose
    long id;


    @SerializedName("poster_path")
     String poster;


    @SerializedName("overview")
    String overview;


    @SerializedName("vote_average")
     String user_rating;


    @SerializedName("release_date")
     String release_date;

    public MovieDetail()
    {}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MovieDetail(Parcel in)
    {
        id = in.readLong();
        title = in.readString();
        poster = in.readString();
        overview = in.readString();
        user_rating = in.readString();
        release_date = in.readString();
    }

    public static final Parcelable.Creator<MovieDetail> CREATOR = new Parcelable.Creator<MovieDetail>() {

        @Override
        public MovieDetail createFromParcel(Parcel source) {
            return new MovieDetail(source);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getPoster() {
        return "http://image.tmdb.org/t/p/w500" +poster;
       // return "http://image.tmdb.org/t/p/w500//inVq3FRqcYIRl2la8iZikYYxFNR.jpg";
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    public void writeToParcel(Parcel parcel,int i)
    {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(overview);
        parcel.writeString(user_rating);
        parcel.writeString(release_date);
    }


    public int describeContents() {
        return 0;
    }


    public class MovieResult {
        private List<MovieDetail> results;

        public List<MovieDetail> getResults()
        {
            return results;
        }
    }
}
