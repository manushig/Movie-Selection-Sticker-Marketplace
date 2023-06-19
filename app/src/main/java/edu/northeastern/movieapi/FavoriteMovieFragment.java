package edu.northeastern.movieapi;

import static android.content.Context.MODE_PRIVATE;

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

    private List<Movie> favoriteMoviesList;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireContext().getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        gson = new Gson();
        favoriteMoviesMap = getFavoriteMoviesMapFromSharedPreferences();
        favoriteMoviesList = new ArrayList<>(favoriteMoviesMap.values());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liked_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textViewEmptyList = view.findViewById(R.id.textViewEmptyList);


        recyclerView = view.findViewById(R.id.recyclerViewFMovies);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoriteMovieAdapter = new FavoriteMovieAdapter(favoriteMoviesList, favoriteMoviesMap, textViewEmptyList);

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
        String moviesJson = gson.toJson(favoriteMoviesMap.values());
        sharedPreferences.edit().putString(PREFERENCES_KEY, moviesJson).apply();
    }

    private void saveSelectedMoviesToSharedPreferences() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("FavoriteMovies", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String selectedMoviesJson = gson.toJson(favoriteMoviesMap.values());
        editor.putString("SelectedMovies", selectedMoviesJson);
        editor.apply();
    }


    private void deleteFavoriteMovie(int position) {
        Movie movie = favoriteMoviesList.get(position);
        String movieId = movie.getId();

        if (favoriteMoviesMap.containsKey(movieId)) {
            favoriteMoviesMap.remove(movieId);
            Toast.makeText(requireContext(), "Movie removed: " + movie.getTitle(), Toast.LENGTH_SHORT).show();

            favoriteMoviesList.remove(position);
            favoriteMovieAdapter.notifyItemRemoved(position);
        }

        saveSelectedMoviesToSharedPreferences();

        checkIfEmptyList();
    }


    private void checkIfEmptyList() {
        if (favoriteMoviesList.isEmpty()) {
            textViewEmptyList.setVisibility(View.VISIBLE);
        } else {
            textViewEmptyList.setVisibility(View.GONE);
        }
    }


}