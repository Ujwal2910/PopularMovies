package app.com.example.songoku.popularmovies;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by songoku on 3/10/17.
 */

public interface MoviesApiService {
    @GET("/movie/popular")
    void getPopularMovies(Callback<MovieDetail.MovieResult> cb);

    @GET("/movie/top_rated")
    void getTopRatedMovies(Callback<MovieDetail.MovieResult> cb);

}
