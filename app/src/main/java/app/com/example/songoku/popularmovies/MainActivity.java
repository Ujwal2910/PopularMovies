package app.com.example.songoku.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mrecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mrecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new MoviesAdapter(this);
        mrecyclerView.setAdapter(mAdapter);
        List<MovieDetail> movies = new ArrayList<>();

        for(int i=0;i<25;i++)
        {
            movies.add(new MovieDetail());

        }


        mAdapter.setMovieList(movies);

        getPopularMovies();
    }

    private void getPopularMovies() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key","eb32f8cd6588af4a97484af6dd7d013b");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        MoviesApiService moviesApiService = restAdapter.create(MoviesApiService.class);
        moviesApiService.getPopularMovies(new Callback<MovieDetail.MovieResult>() {
            @Override
            public void success(MovieDetail.MovieResult movieResult, Response response) {

                mAdapter.setMovieList(movieResult.getResults());

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();

            }
        });
    }
    private void getRatedMovies() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key","eb32f8cd6588af4a97484af6dd7d013b");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        MoviesApiService moviesApiService = restAdapter.create(MoviesApiService.class);
        //long id=25;
        moviesApiService.getTopRatedMovies( new Callback<MovieDetail.MovieResult>() {
            @Override
            public void success(MovieDetail.MovieResult movieResult, Response response) {
                mAdapter.setMovieList(movieResult.getResults());

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();

            }
        });
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.Order_popular)
        {
            getPopularMovies();
        }
        if(id == R.id.Order_ratings)
        {
            getRatedMovies();
        }
        return super.onOptionsItemSelected(item);
    }
}
