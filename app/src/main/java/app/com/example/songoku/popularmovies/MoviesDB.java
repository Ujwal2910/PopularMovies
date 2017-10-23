package app.com.example.songoku.popularmovies;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by songoku on 23/10/17.
 */

public class MoviesDB {
    static final String AUTHORITY_Uri = "content://" + MoviesContract.AUTHORITY;

    public boolean isMovieFavorited(ContentResolver contentResolver, long id)
    {
        boolean ret = false;
        Cursor cursor = contentResolver.query(Uri.parse(AUTHORITY_Uri + "/" + id), null, null, null, null, null);
        if (cursor != null && cursor.moveToNext()){
            ret = true;
            cursor.close();
        }
        return ret;
    }
    public void addMovie( ContentResolver contentResolver,MovieDetail movieDetail)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesContract.MovieEntry.COLUMN_ID,movieDetail.id);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_NAME,movieDetail.title);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW,movieDetail.overview);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_POSTER,movieDetail.poster);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_RATING,movieDetail.user_rating+"");
        contentValues.put(MoviesContract.MovieEntry.COLUMN_RELEASE,movieDetail.release_date);
        contentResolver.insert(Uri.parse(AUTHORITY_Uri+"/movies"),contentValues);
    }

    public void removeMovie(ContentResolver contentResolver, long id){
        Uri uri = Uri.parse(AUTHORITY_Uri + "/" + id);
        contentResolver.delete(uri, null, new String[]{id + ""});
    }
    public ArrayList<MovieDetail> getFavouriteMovies(ContentResolver contentResolver)
    {
        Uri uri = Uri.parse(AUTHORITY_Uri+"/movies");
        Cursor cursor = contentResolver.query(uri, null, null, null, null, null);
        ArrayList<MovieDetail> movies = new ArrayList<>();

        if (cursor !=null && cursor.moveToFirst())
        {
            do {
                MovieDetail movieDetail = new MovieDetail();
                movieDetail.id = cursor.getLong(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ID));
                movieDetail.title = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_NAME));
                movieDetail.overview = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_OVERVIEW));
                movieDetail.user_rating = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RATING));
                movieDetail.poster = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POSTER));
                movieDetail.release_date = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE));
                movies.add(movieDetail);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return movies;
    }

}
