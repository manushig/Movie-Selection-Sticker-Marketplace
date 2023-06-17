package edu.northeastern.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.adapters.TrailerAdapter;
import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.MovieWebService;

public class TrailerFragment extends Fragment {
    TrailerAdapter trailerAdapter;
    private MovieWebService movieWebService;
    private OnItemActionListener onItemActionListener;
    Context context;


    List<Movie> movieList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trailer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressBar progressBar = view.findViewById(R.id.progressBar2);
        initializeItemActionListener();
        trailerAdapter = new TrailerAdapter(movieList, onItemActionListener);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(trailerAdapter);


        MovieWebService.UiThreadCallback uiThreadCallback = new MovieWebService.UiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
                progressBar.setVisibility(View.GONE);
                movieList.addAll(movies);
                trailerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDetailGet(MovieDetail movieDetails) {

            }

            @Override
            public void onVideoGet(YoutubeVideo youtubeVideo) {

            }

        };

        movieWebService = new MovieWebService(uiThreadCallback);
        movieWebService.getNowPlaying();
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initializeItemActionListener() {
        onItemActionListener = new OnItemActionListener() {
            @Override
            public void onClick(Movie movie) {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("movieId",movie.getId());
                startActivity(intent);
            }
        };
    }

}