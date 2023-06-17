package edu.northeastern.movieapi;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.search_badge) {
                    replaceFragment(SearchFragment.class);
                } else if (item.getItemId() == R.id.now_playing) {
                    replaceFragment(TrailerFragment.class);
                } else if (item.getItemId() == R.id.support) {
                    replaceFragment(SupportFragment.class);
                }
                return false;
            }
        });

        showSearchFragment();
    }

    private void replaceFragment(Class fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, fragment, null)
                .commit();
    }

    private void showSearchFragment() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, TrailerFragment.class, null)
                .commit();
    }
}
