package app.com.example.songoku.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.IllegalFormatException;

/**
 * Created by songoku on 4/10/17.
 */

public class MovieDescription extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";

    private MovieDetail mmovieDetail;
    ImageView movie_poster;
    TextView movie_title;
    TextView movie_desc;
    TextView movie_rating;
    TextView release_date;
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

        movie_poster = (ImageView)findViewById(R.id.movie_img_view);
        movie_title = (TextView) findViewById(R.id.movie_title);
        movie_desc = (TextView) findViewById(R.id.movie_desc);
        movie_rating = (TextView) findViewById(R.id.movie_rating);
        release_date = (TextView) findViewById(R.id.release_date);

        movie_title.setText(mmovieDetail.getTitle());
        movie_desc.setText(mmovieDetail.getOverview());
        movie_rating.setText(mmovieDetail.getUser_rating());
        release_date.setText(mmovieDetail.getRelease_date());

        Picasso.with(this)
                .load(mmovieDetail.getPoster())
                .into(movie_poster);



    }
}
