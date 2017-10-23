package app.com.example.songoku.popularmovies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by songoku on 23/10/17.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "movies.db";
    static final int DATABASE_VERSION = 1;
    public MovieDBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "create table " + MoviesContract.MovieEntry.TABLE_NAME + " (" +
                MoviesContract.MovieEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                MoviesContract.MovieEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                MoviesContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MoviesContract.MovieEntry.COLUMN_POSTER + " TEXT NOT NULL, " +
                MoviesContract.MovieEntry.COLUMN_RATING + " TEXT NOT NULL, " +
                MoviesContract.MovieEntry.COLUMN_RELEASE + " TEXT NOT NULL" +
                ")";
        Log.d("TABLE", "creating table " + CREATE_TABLE);
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ MoviesContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
