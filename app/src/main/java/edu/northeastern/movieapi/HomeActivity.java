package edu.northeastern.movieapi;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    private int currentSelectedItemIndex = 0;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Class fragmentClass = getFragmentClassBasedOnId(item.getItemId());
                replaceFragment(fragmentClass, item.getItemId());
                currentSelectedItemIndex = item.getItemId();
                return true;
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

    private void replaceFragment(Class fragment, int index) {
        String tag = "fragment_" + index;
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragmentByTag == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, fragment, null, tag)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, fragmentByTag, tag)
                    .commit();
        }

    }

    private void showInitialFragment(Bundle savedInstanceState) {
        Class initialFragment = null;
        if (savedInstanceState == null) {
            initialFragment = SearchFragment.class;
            String tag = "fragment_" + R.id.search_badge;
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, initialFragment, null, tag)
                    .commit();
        } else {
            int itemIndex = savedInstanceState.getInt("current_selected_item_index");
            initialFragment = getFragmentClassBasedOnId(itemIndex);
            replaceFragment(initialFragment, itemIndex);
        }
    }

    @Override
    public void onBackPressed() {
        if (currentSelectedItemIndex != R.id.search_badge) {
            replaceFragment(SearchFragment.class);
            bottomNavigationView.setSelectedItemId(R.id.search_badge);
            currentSelectedItemIndex = R.id.search_badge;
        } else {
            super.onBackPressed();
        }
    }
}
