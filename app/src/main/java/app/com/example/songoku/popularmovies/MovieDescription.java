package app.com.example.songoku.popularmovies;

import android.content.ContentResolver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by songoku on 4/10/17.
 */

public class MovieDescription extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";

    public FloatingActionButton fab;

    private MovieDetail mmovieDetail;
    long movieId;
    ImageView movie_poster;
    TextView movie_title;
    TextView movie_desc;
    TextView movie_rating;


    TextView release_date;

    @Bind(R.id.text_reviews_title)
    RecyclerView movieReviewsRecyclerView;

    @Bind(R.id.text_trailer_title)
            RecyclerView movieVideosRecyclerView;

    ReviewAdapter mreviewAdapter;
    VideoAdapter mvideosAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetails);
        if(getIntent().hasExtra(EXTRA_MOVIE))
        {
            mmovieDetail = getIntent().getParcelableExtra(EXTRA_MOVIE);
        }
        else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }



        mreviewAdapter = new ReviewAdapter(this);
        mvideosAdapter = new VideoAdapter(this);

        movie_poster = (ImageView)findViewById(R.id.movie_img_view);
        movie_title = (TextView) findViewById(R.id.movie_title);
        movie_desc = (TextView) findViewById(R.id.movie_desc);
        movie_rating = (TextView) findViewById(R.id.movie_rating);
        release_date = (TextView) findViewById(R.id.release_date);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new FabOnClick());



        movieId = mmovieDetail.getId();
        movie_title.setText(mmovieDetail.getTitle());
        movie_desc.setText(mmovieDetail.getOverview());
        movie_rating.setText(mmovieDetail.getUser_rating());

        movieReviewsRecyclerView = (RecyclerView)findViewById(R.id.layout_reviews_list);
        movieReviewsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayoutManager.HORIZONTAL));
        movieReviewsRecyclerView.setAdapter(mreviewAdapter);

        movieVideosRecyclerView = (RecyclerView)findViewById( R.id.layout_trailers_list);
        movieVideosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieVideosRecyclerView.setAdapter(mvideosAdapter);


        release_date.setText(mmovieDetail.getRelease_date());

        Picasso.with(this)
                .load(mmovieDetail.getPoster())
                .into(movie_poster);

        loadMovieReviews(movieId);
        loadMovieVideos(movieId);
        updateUI();


    }

    private void updateUI() {

        MoviesDB moviesDB = new MoviesDB();
        boolean favstats = moviesDB.isMovieFavorited(getApplicationContext().getContentResolver(),mmovieDetail.id);
        if (favstats)
        {
            fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),android.R.drawable.btn_star_big_on));
        }
        else {
            fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),android.R.drawable.btn_star_big_off));
        }

    }

    public void loadMovieReviews(long id)
    {
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
        moviesApiService.getReviews(id, new Callback<Review_list>() {
            @Override
            public void success(Review_list review_list, Response response) {

              mreviewAdapter.setReviewList(review_list.getReviews());
                mreviewAdapter.notifyDataSetChanged();
                movieReviewsRecyclerView.setAdapter(mreviewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void loadMovieVideos(long id)
    {
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
        moviesApiService.getVideos(id, new Callback<Video_list>() {
            @Override
            public void success(Video_list video_list, Response response) {
                mvideosAdapter.setVideosList(video_list.getVideos());
                mvideosAdapter.notifyDataSetChanged();
                movieVideosRecyclerView.setAdapter(mvideosAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    private class FabOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ContentResolver contentResolver= getApplicationContext().getContentResolver();
            MoviesDB mdb = new MoviesDB();
            String message;
            if (mdb.isMovieFavorited(contentResolver,mmovieDetail.id))
            {
                message = "Removed from Favorites";
                mdb.removeMovie(contentResolver,mmovieDetail.id);
                fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),android.R.drawable.btn_star_big_off));
            }
            else {
                mdb.addMovie(contentResolver,mmovieDetail);
                message = "Added to Favorites";
                fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),android.R.drawable.btn_star_big_on));
            }
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        }
    }
}
