package edu.northeastern.movieapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.northeastern.movieapi.adapters.FavoriteMovieAdapter;
import edu.northeastern.movieapi.adapters.MovieListAdapter;
import edu.northeastern.movieapi.model.Movie;

public class FavoriteMovieFragment extends Fragment {
    private static final String PREFERENCES_NAME = "FavoriteMovies";
    private static final String PREFERENCES_KEY = "SelectedMovies";

    private SharedPreferences sharedPreferences;
    private FavoriteMovieAdapter favoriteMovieAdapter;
    private Map<String, Movie> favoriteMoviesMap;
    private TextView textViewEmptyList;

    private Gson gson;

    private RecyclerView recyclerView;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        favoriteMoviesMap = getFavoriteMoviesMapFromSharedPreferences();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liked_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textViewEmptyList = view.findViewById(R.id.textViewEmptyList);

        List<Movie> favoriteMoviesList = new ArrayList<>(favoriteMoviesMap.values());

        recyclerView = view.findViewById(R.id.recyclerViewFMovies);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoriteMovieAdapter = new FavoriteMovieAdapter(favoriteMoviesList);

        recyclerView.setAdapter(favoriteMovieAdapter);

        favoriteMovieAdapter.setOnItemClickListener(new FavoriteMovieAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteFavoriteMovie(position);
            }
        });

        checkIfEmptyList();


    }

    private Map<String, Movie> getFavoriteMoviesMapFromSharedPreferences() {
        Set<String> selectedMoviesJsonSet = sharedPreferences.getStringSet(PREFERENCES_KEY, new HashSet<>());
        Map<String, Movie> selectedMoviesMap = new HashMap<>();

        for (String selectedMovieJson : selectedMoviesJsonSet) {
            Movie movie = gson.fromJson(selectedMovieJson, Movie.class);
            selectedMoviesMap.put(movie.getId(), movie);
        }

        return selectedMoviesMap;


    }

    private void saveFavoriteMoviesMapToSharedPreferences(Map<String, Movie> favoriteMoviesMap) {
        Gson gson = new Gson();
        String moviesJson = gson.toJson(favoriteMoviesMap);
        sharedPreferences.edit().putString(PREFERENCES_KEY, moviesJson).apply();
    }


    private void deleteFavoriteMovie(int position) {
        String[] movieIds = favoriteMoviesMap.keySet().toArray(new String[0]);
        String movieId = movieIds[position];
        Movie deletedMovie = favoriteMoviesMap.get(movieId);
        favoriteMoviesMap.remove(movieId);
        saveFavoriteMoviesMapToSharedPreferences(favoriteMoviesMap);
        favoriteMovieAdapter.notifyItemRemoved(position); // Notify the adapter of item removal
        Toast.makeText(requireContext(), "Movie removed: " + deletedMovie.getTitle(), Toast.LENGTH_SHORT).show();
        checkIfEmptyList();
    }

    private void checkIfEmptyList() {
        if (favoriteMoviesMap.isEmpty()) {
            textViewEmptyList.setVisibility(View.VISIBLE);
        } else {
            textViewEmptyList.setVisibility(View.GONE);
        }
    }


}
