package app.com.example.songoku.popularmovies;

import java.util.ArrayList;

/**
 * Created by songoku on 21/10/17.
 */

public class Video_list {
    long id;

    public long getId() {
        return id;
    }

    ArrayList<MovieVideo> results;

    public ArrayList<MovieVideo> getVideos() {
        return results;
    }
}
