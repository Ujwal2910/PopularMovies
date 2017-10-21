package app.com.example.songoku.popularmovies;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by songoku on 21/10/17.
 */

public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> mValues;

    private Context context;
    public ReviewAdapter(List<Review> items)
    {
        mValues = items;
    }


    public ReviewAdapter( Context context) {
        this.mValues = new ArrayList<Review>();
        this.context = context;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, int position) {
        final Review review = mValues.get(position);

        holder.mAuthor.setText(review.getAuthor());
        holder.mAuthor.setTextIsSelectable(true);
        holder.mAuthor.setTextSize(16);
        holder.mAuthor.setTypeface(Typeface.DEFAULT_BOLD);
        holder.mContent.setText(review.getContent());
        holder.mContent.setTypeface(Typeface.SANS_SERIF);

    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(android.R.id.text1)
        TextView mAuthor;

        @Bind(android.R.id.text2)
        TextView mContent;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

    public void setReviewList(List<Review> reviewList)
    {
        this.mValues.clear();
        this.mValues.addAll(reviewList);
        //
        notifyDataSetChanged();
    }
}
