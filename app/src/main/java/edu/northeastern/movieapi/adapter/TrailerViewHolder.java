package edu.northeastern.movieapi.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.northeastern.movieapi.R;
import edu.northeastern.movieapi.model.Movie;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    TextView movieTitle;
    TextView movieDetail;
    ImageView movieImage;
    View itemView;
    public TrailerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.movieTitle=itemView.findViewById(R.id.movieTitleTextView);
        this.movieDetail=itemView.findViewById(R.id.movieDetailTextView);
        this.movieImage=itemView.findViewById(R.id.movieImageView);
    }
}
