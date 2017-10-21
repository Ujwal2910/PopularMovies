package app.com.example.songoku.popularmovies;

/**
 * Created by songoku on 21/10/17.
 */

public class MovieVideo {
    public static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    String id;

    String key;
    String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getVideoUrl() {
        return YOUTUBE_URL + getKey();
    }
}
