package edu.northeastern.movieapi;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomAppBar bottomAppBar;
    private int currentSelectedItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Class fragmentClass = getFragmentClassBasedOnId(item.getItemId());
                replaceFragment(fragmentClass);
                currentSelectedItemIndex = item.getItemId();
                return false;
            }
        });

        if (savedInstanceState == null) {
            currentSelectedItemIndex = R.id.search_badge;
        } else {
            currentSelectedItemIndex = savedInstanceState.getInt("current_selected_item_index");
        }
        showInitialFragment(savedInstanceState);
    }

    private Class getFragmentClassBasedOnId(int itemId) {
        if (itemId == R.id.search_badge) {
            return SearchFragment.class;
        } else if (itemId == R.id.now_playing) {
            return TrailerFragment.class;
        } else if (itemId == R.id.support) {
            return SupportFragment.class;
        }
        return null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("current_selected_item_index", currentSelectedItemIndex);
        super.onSaveInstanceState(outState);
    }

    private void replaceFragment(Class fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, fragment, null)
                .commit();
    }

    private void showInitialFragment(Bundle savedInstanceState) {
        Class initialFragment = null;
        if (savedInstanceState == null) {
            initialFragment = SearchFragment.class;
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, initialFragment, null)
                    .commit();
        } else {
            initialFragment = getFragmentClassBasedOnId(savedInstanceState.getInt("current_selected_item_index"));
            replaceFragment(initialFragment);
        }
    }
}
