package edu.northeastern.stickers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.northeastern.movieapi.FavoriteMovieFragment;
import edu.northeastern.movieapi.R;


public class StickerHomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private int currentSelectedItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_home);
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
            currentSelectedItemIndex = R.id.sticker_store;
        } else {
            currentSelectedItemIndex = savedInstanceState.getInt("current_selected_item_index");
        }
        showInitialFragment(savedInstanceState);
    }

    private void showInitialFragment(Bundle savedInstanceState) {
        Class initialFragment = null;
        if (savedInstanceState == null) {
            initialFragment = StickerStoreFragment.class;
            String tag = "fragment_" + R.id.sticker_store;
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

    private void replaceFragment(Class fragmentClass, int itemId) {
        String tag = "fragment_" + itemId;
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragmentByTag == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, fragmentClass, null, tag)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, fragmentByTag, tag)
                    .commit();
        }
    }

    private Class getFragmentClassBasedOnId(int itemId) {
        if (itemId == R.id.sticker_store) {
            return StickerStoreFragment.class;
        } else if (itemId == R.id.sticker_inbox) {
            return StickerInboxFragment.class;
        } else if (itemId == R.id.sticker_history) {
            return StickerHistoryFragment.class;
        }
        return null;
    }
    @Override
    public void onBackPressed() {
        if (currentSelectedItemIndex != R.id.sticker_store) {
            replaceFragment(StickerStoreFragment.class,currentSelectedItemIndex);
            bottomNavigationView.setSelectedItemId(R.id.sticker_store);
            currentSelectedItemIndex = R.id.sticker_store;
        } else {
            super.onBackPressed();
        }
    }
}