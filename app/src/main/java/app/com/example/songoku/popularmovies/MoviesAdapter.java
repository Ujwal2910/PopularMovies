package app.com.example.songoku.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.example.songoku.popularmovies.MainActivity;

/**
 * Created by songoku on 3/10/17.
 */


public class MoviesAdapter extends RecyclerView.Adapter<MainActivity.MovieViewHolder> {
    private List<MovieDetail> mMovieList;
    private LayoutInflater layoutInflater;
    private Context mcontext;

    public MoviesAdapter(Context context) {
        this.mMovieList = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
        this.mcontext = context;
    }

    @Override
    public MainActivity.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.row_movie,parent,false);
        final MainActivity.MovieViewHolder movieViewHolder =new MainActivity.MovieViewHolder(view);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = movieViewHolder.getAdapterPosition();
                Intent intent = new Intent(mcontext, MovieDescription.class);
                intent.putExtra(MovieDescription.EXTRA_MOVIE, mMovieList.get(position));
                mcontext.startActivity(intent);
            }
        });
        return movieViewHolder;

    }

    @Override
    public void onBindViewHolder(MainActivity.MovieViewHolder holder, int position) {
//        MovieDetail movieDetail = new MovieDetail();
        Picasso.with(mcontext)
                .load(mMovieList.get((position))
                        .getPoster())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    //notify data has changed
    public void setMovieList(List<MovieDetail> movieList)
    {
        this.mMovieList.clear();
        this.mMovieList.addAll(movieList);
        //
        notifyDataSetChanged();
    }
}
