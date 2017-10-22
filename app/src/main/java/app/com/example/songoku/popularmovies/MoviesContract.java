package app.com.example.songoku.popularmovies;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by songoku on 23/10/17.
 */

public class MoviesContract {

    public static final String AUTHORITY = "com.example.songoku.popularmovies";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    public MoviesContract() {
    }

    public static final class MovieEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME = "movie_name";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RELEASE = "released_date";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER = "poster_url";
        public static final String COLUMN_ID = "_id";
    }
}
