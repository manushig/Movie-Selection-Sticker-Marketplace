package edu.northeastern.movieapi.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.northeastern.movieapi.R;
import edu.northeastern.movieapi.model.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private Map<String, Movie> selectedMovies;

    private Gson gson;
    private Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onImageClick(int position);

        void onFavoriteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MovieListAdapter(List<Movie> movies, Context context, Map<String, Movie> selectedMovies) {
        this.movies = movies;
        this.context = context;
        this.selectedMovies  = selectedMovies;
        this.gson = new Gson();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_row, parent, false);
        return new MovieViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        // Update the layout dynamically
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(holder.constraintLayout);

        // Set the movie-specific attributes
        // Load image resource using Glide or any other image loading library
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

        boolean isSelected = selectedMovies.containsKey(movie.getId());

        if (selectedMovies.containsKey(movie.getId())) {
            holder.imageViewHeart.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            holder.imageViewHeart.setImageResource(R.drawable.baseline_favorite_border_24);
        }

        holder.imageViewHeart.setOnClickListener(v -> {
            if (selectedMovies.containsKey(movie.getId())) {
                selectedMovies.remove(movie.getId());
                holder.imageViewHeart.setImageResource(R.drawable.baseline_favorite_border_24);
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                selectedMovies.put(movie.getId(), movie);
                holder.imageViewHeart.setImageResource(R.drawable.baseline_favorite_24);
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
            saveSelectedMoviesToSharedPreferences();
        });

        // Apply the updated layout
        constraintSet.applyTo(holder.constraintLayout);
    }

    private void saveSelectedMoviesToSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("FavoriteMovies", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("SelectedMovies", getSelectedMoviesJsonSet());
        editor.apply();
    }
    private List<String> getSelectedMoviesJsonList() {
        List<String> selectedMoviesJsonList = new ArrayList<>();
        for (Movie movie : selectedMovies.values()) {
            String movieJson = gson.toJson(movie);
            selectedMoviesJsonList.add(movieJson);
        }
        return selectedMoviesJsonList;
    }

    private String[] getSelectedMoviesJsonArray() {
        List<String> selectedMoviesJsonList = getSelectedMoviesJsonList();
        return selectedMoviesJsonList.toArray(new String[0]);
    }

    private String getSelectedMoviesJsonString() {
        String[] selectedMoviesJsonArray = getSelectedMoviesJsonArray();
        return gson.toJson(selectedMoviesJsonArray);
    }

    private HashSet<String> getSelectedMoviesJsonSet() {
        String selectedMoviesJsonString = getSelectedMoviesJsonString();
        String[] selectedMoviesJsonArray = gson.fromJson(selectedMoviesJsonString, String[].class);
        return new HashSet<>(java.util.Arrays.asList(selectedMoviesJsonArray));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        ImageView imageViewMovie;
        TextView textViewMovieTitle;
        TextView textViewContentRating;
        TextView textViewMovieRating;
        TextView textViewMovieDuration;
        ImageView imageViewPlay;
        ImageView imageViewHeart;

        public MovieViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageViewPlay = itemView.findViewById(R.id.imageViewPlay);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutMovieItem);
            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
            textViewMovieTitle = itemView.findViewById(R.id.textViewMovieTitle);
            textViewContentRating = itemView.findViewById(R.id.textViewContentRating);
            textViewMovieRating = itemView.findViewById(R.id.textViewMovieRating);
            textViewMovieDuration = itemView.findViewById(R.id.textViewMovieDuration);
            imageViewHeart = itemView.findViewById(R.id.imageViewHeart);

            imageViewPlay.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onImageClick(position);
                    }
                }
            });

            constraintLayout.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

            imageViewHeart.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onFavoriteClick(position);
                    }
                }
            });
        }
    }
}
