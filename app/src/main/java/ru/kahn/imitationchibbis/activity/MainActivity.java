package ru.kahn.imitationchibbis.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.kahn.imitationchibbis.R;
import ru.kahn.imitationchibbis.fragments.FragmentHits;
import ru.kahn.imitationchibbis.fragments.FragmentRestaurants;
import ru.kahn.imitationchibbis.fragments.FragmentReviews;

public class MainActivity extends AppCompatActivity {

    final Fragment fragmentRestaurants = new FragmentRestaurants();
    final Fragment fragmentHits = new FragmentHits();
    final Fragment fragmentReviews = new FragmentReviews();
    FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentRestaurants;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, fragmentReviews, "fragmentReviews").hide(fragmentReviews).commit();
        fm.beginTransaction().add(R.id.main_container, fragmentHits, "fragmentHits").hide(fragmentHits).commit();
        fm.beginTransaction().add(R.id.main_container, fragmentRestaurants, "fragmentRestaurants").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_restaurants:
                    fm.beginTransaction().hide(active).show(fragmentRestaurants).commit();
                    active = fragmentRestaurants;
                    return true;
                case R.id.action_hit:
                    fm.beginTransaction().hide(active).show(fragmentHits).commit();
                    active = fragmentHits;
                    return true;
                case R.id.action_reviews:
                    fm.beginTransaction().hide(active).show(fragmentReviews).commit();
                    active = fragmentReviews;
                    return true;
            }
            return false;
        }
    };
}
