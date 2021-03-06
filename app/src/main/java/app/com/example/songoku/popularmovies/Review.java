package app.com.example.songoku.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by songoku on 21/10/17.
 */

public class Review implements Parcelable {

    @Expose
    private String id;
    @Expose
    private String author;
    @Expose
    private String content;
    @Expose
    private String url;

    public Review()
    {}

    public String getId()
    {
        return id;
    }
    public Review setId(String id)
    {
        this.id = id;
        return this;
    }
    public String getAuthor()
    {
        return author;
    }

    public Review setAuthor(String content)
    {
        this.content = content;
        return this;
    }
    public String getContent()
    {
        return content;
    }
    public Review setContent(String content)
    {
        this.content = content;
        return this;
    }

    public String getUrl()
    {
        return url;
    }
    public Review setUrl(String url)
    {
        this.url = url;
        return this;
    }


    protected Review(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

    public static final class Response {
        @Expose
        public long id;

        @Expose
        public int page;

        @Expose @SerializedName("results")
        public List<Review> reviews;

        @Expose @SerializedName("total_pages")
        public int totalPages;

        @Expose @SerializedName("total_results")
        public int totalResults;

    }
}
