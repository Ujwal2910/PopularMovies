package app.com.example.songoku.popularmovies;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by songoku on 3/10/17.
 */

public interface MoviesApiService {
    public static final String api_key ="eb32f8cd6588af4a97484af6dd7d013b";
    @GET("/movie/popular")
    void getPopularMovies(Callback<MovieDetail.MovieResult> cb);

    @GET("/movie/top_rated")
    void getTopRatedMovies(Callback<MovieDetail.MovieResult> cb);

    @GET("/movie/{id}/reviews")
    void getReviews(@Path("id") long id, Callback<Review_list> cb);

    @GET("/movie/{id}/videos")
    void getVideos(@Path("id") long id, Callback<Video_list> cb);


}
