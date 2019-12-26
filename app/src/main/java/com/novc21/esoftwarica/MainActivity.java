package com.novc21.esoftwarica;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.novc21.esoftwarica.adapter.Students;
import com.novc21.esoftwarica.fragment.AboutFragment;
import com.novc21.esoftwarica.fragment.AddFragment;
import com.novc21.esoftwarica.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Students> studentsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (studentsList.isEmpty()) {
            studentsList.add(new Students("Manisha", "female", "Baneshwor", 20));
            studentsList.add(new Students("Supriya", "female", "Kalanki", 22));
            studentsList.add(new Students("Jackie", "male", "Zook", 21));
            studentsList.add(new Students("Lucky", "others", "Buddhanagar", 19));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.navHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navAddStudent:
                    selectedFragment = new AddFragment();
                    break;
                case R.id.navAboutUs:
                    selectedFragment = new AboutFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
        }
    };

}
