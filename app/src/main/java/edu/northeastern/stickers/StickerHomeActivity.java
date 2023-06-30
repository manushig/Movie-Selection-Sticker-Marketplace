package edu.northeastern.stickers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import edu.northeastern.movieapi.FavoriteMovieFragment;
import edu.northeastern.movieapi.MainActivity;
import edu.northeastern.movieapi.R;


public class StickerHomeActivity extends AppCompatActivity implements LogoutDialogListener {
    BottomNavigationView bottomNavigationView;
    private int currentSelectedItemIndex = 0;
    private ImageView logoutImageView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_home);

        auth = FirebaseAuth.getInstance();

        logoutImageView = findViewById(R.id.logoutImageView);

        logoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutDialogFragment dialog = new LogoutDialogFragment();
                dialog.setLogoutDialogListener((LogoutDialogListener) StickerHomeActivity.this);
                dialog.show(getSupportFragmentManager(), "logout_dialog");
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                currentSelectedItemIndex = item.getItemId();
                Class fragmentClass = getFragmentClassBasedOnId(item.getItemId());
                replaceFragment(fragmentClass, item.getItemId());
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("current_selected_item_index", currentSelectedItemIndex);
        super.onSaveInstanceState(outState);
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
            initialFragment = getFragmentClassBasedOnId(currentSelectedItemIndex);
            replaceFragment(initialFragment, currentSelectedItemIndex);
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
            replaceFragment(StickerStoreFragment.class, currentSelectedItemIndex);
            bottomNavigationView.setSelectedItemId(R.id.sticker_store);
            currentSelectedItemIndex = R.id.sticker_store;
        } else {
            if (auth.getCurrentUser() != null) {
                startActivity(new Intent(StickerHomeActivity.this, MainActivity.class));
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onLogoutConfirmed() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(StickerHomeActivity.this, LoginActivity.class));
        finish();
    }
}