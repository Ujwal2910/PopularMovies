package app.com.example.songoku.popularmovies;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songoku on 21/10/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context context;
    private final List<MovieVideo> mValue;

    public VideoAdapter(Context context) {
        this.context = context;
        this.mValue = new ArrayList<MovieVideo>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.activity_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MovieVideo movieVideo = mValue.get(position);

        holder.mVideoName.setText(movieVideo.getName());
       holder.mVideoName.setTextSize(20);
       // holder.mvideoImage.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
        Picasso.with(context).load("http://img.youtube.com/vi/" + movieVideo.getKey() + "/default.jpg")
                .into(holder.mvideoImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchYoutubeVideo(movieVideo.getKey());
            }
        });

    }

    private void watchYoutubeVideo(String key) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + key));
            context.startActivity(intent);
        }

    }

    @Override
    public int getItemCount() {
        return mValue.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       @SuppressLint("ResourceType")
       @BindView(value = android.R.id.text1)
        TextView mVideoName;
        @SuppressLint("ResourceType")
        @BindView(android.R.id.icon)
        ImageView mvideoImage ;
        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
    public void setVideosList(List<MovieVideo> movieVideoList)
    {
        this.mValue.clear();
        this.mValue.addAll(movieVideoList);
        //
        notifyDataSetChanged();

    }
}
