package edu.northeastern.movieapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.movieapi.model.Movie;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {

    private List<Movie> favoriteMoviesList;

    private OnItemClickListener mListener;

    private Context context;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }
    public FavoriteMovieAdapter(List<Movie> favoriteMoviesList) {
        this.favoriteMoviesList = favoriteMoviesList;

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favourite_item_row, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = favoriteMoviesList.get(position);

        Glide.with(holder.itemView)
                .load(movie.getImage())
                .into(holder.imageViewMovie);


        String movieTitle = movie.getTitle();
        String contentRating = movie.getContentRating();
        String duration = movie.getRuntimeStr();
        String imdbRating = movie.getImDbRating();

        if (movieTitle != null && !movieTitle.isEmpty() && !(movieTitle.trim().equals("null"))) {
            holder.textViewMovieTitle.setText(movieTitle);
        } else {
            holder.textViewMovieTitle.setText(R.string.result_no_title);
        }

        if (contentRating != null && !contentRating.isEmpty() && !(contentRating.trim().equals("null"))) {
            holder.textViewContentRating.setText(contentRating);
        } else {
            holder.textViewContentRating.setText(R.string.result_no_content_rating);
        }

        boolean durationAvailable = (duration != null && !duration.isEmpty() && !(duration.trim().equals("null")));

        holder.textViewMovieDuration.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);

        if (durationAvailable) {
            holder.textViewMovieDuration.setText(duration);
        } else {
            holder.textViewMovieDuration.setText("");
        }

        if (imdbRating != null && !imdbRating.isEmpty() && !(imdbRating.trim().equals("null"))) {
            if (!durationAvailable) {
                holder.textViewMovieDuration.setText(imdbRating);
                holder.textViewMovieDuration.setVisibility(View.VISIBLE);
                holder.textViewMovieDuration.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_star_rate_24, 0, 0, 0);

                holder.textViewMovieRating.setText("");
                holder.textViewMovieRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            } else {

                holder.textViewMovieRating.setVisibility(View.VISIBLE);
                holder.textViewMovieRating.setText(imdbRating);
                holder.textViewMovieRating.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_star_rate_24, 0, 0, 0);
            }
        } else {
            holder.textViewMovieRating.setText("");
            holder.textViewMovieRating.setVisibility(View.GONE);
            holder.textViewMovieRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        }

    }

    @Override
    public int getItemCount() {
        return favoriteMoviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewMovie;
        private TextView textViewMovieTitle;

        private TextView textViewMovieDuration;
        private TextView textViewMovieRating;
        private TextView textViewContentRating;
        private ImageView imageViewDelete;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageViewMovie = itemView.findViewById(R.id.imageViewFMovie);
            textViewMovieTitle = itemView.findViewById(R.id.textViewFMovieTitle);
            textViewContentRating   = itemView.findViewById(R.id.textViewFContentRating);
            textViewMovieDuration   = itemView.findViewById(R.id.textViewFMovieDuration);
            textViewMovieRating   = itemView.findViewById(R.id.textViewFMovieRating);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);


            imageViewDelete.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });
        }


    }
}
