package edu.northeastern.movieapi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.MovieWebService;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movieId");

        MovieWebService.UiThreadCallback uiThreadCallback = new MovieWebService.UiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
            }

            @Override
            public void onDetailGet(MovieDetail movieDetails) {
            }

            @Override
            public void onVideoGet(YoutubeVideo youtubeVideo) {
                YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
                getLifecycle().addObserver(youTubePlayerView);
                youTubePlayerView.setEnableAutomaticInitialization(false);
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideo.getVideoId(), 0);
                    }
                });

            }
        };
        MovieWebService movieWebService = new MovieWebService(uiThreadCallback);
        movieWebService.getVideo(movieId);
    }
}