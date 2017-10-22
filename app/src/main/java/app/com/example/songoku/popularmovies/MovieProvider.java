package app.com.example.songoku.popularmovies;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by songoku on 23/10/17.
 */

public class MovieProvider extends ContentProvider{

    private static final int MOVIE_DETAIL = 2;
    private static final int MOVIE_LIST = 1;
    static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(MoviesContract.AUTHORITY,"movies",MOVIE_LIST);
        sUriMatcher.addURI(MoviesContract.AUTHORITY,"#",MOVIE_DETAIL);
    }

    static String LOG_TAG = "Database";
    MovieDBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    public boolean onCreate() {

        dbHelper = new MovieDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor retCursor;
        if (sortOrder == null) sortOrder = MoviesContract.MovieEntry.COLUMN_ID;
        switch (sUriMatcher.match(uri)){
            case MOVIE_LIST: {
                retCursor = sqLiteDatabase.query(
                        MoviesContract.MovieEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            }
            case MOVIE_DETAIL: {
                retCursor = sqLiteDatabase.query(
                        MoviesContract.MovieEntry.TABLE_NAME, projection, MoviesContract.MovieEntry.COLUMN_ID + " = ?",
                        new String[]{uri.getLastPathSegment()}, null, null, sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri returnUri;
        long id = sqLiteDatabase.insert(MoviesContract.MovieEntry.TABLE_NAME,null,values);
        Log.v(LOG_TAG,"INserted - id = " +id);
        if (id>0)
        {
            returnUri = ContentUris.withAppendedId(MoviesContract.CONTENT_URI,id);
            getContext().getContentResolver().notifyChange(returnUri,null);
            return returnUri;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (sUriMatcher.match(uri)){
            case MOVIE_LIST: {
                count = sqLiteDatabase.delete(MoviesContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case MOVIE_DETAIL: {
                count = sqLiteDatabase.delete(MoviesContract.MovieEntry.TABLE_NAME, MoviesContract.MovieEntry.COLUMN_ID + " = ?", selectionArgs);
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("not needed");
    }
}
