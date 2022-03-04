package com.areeb.adminpatholab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;


import com.areeb.adminpatholab.Fragments.home;
import com.areeb.adminpatholab.Fragments.profile;
import com.areeb.adminpatholab.Fragments.slots;
import com.areeb.adminpatholab.Fragments.treatset;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navbar = findViewById(R.id.navnbar);


        navbar.setSelectedItemId(R.id.homeFragments);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();

        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch ((item.getItemId())) {
                    case R.id.homeFragments:
                        fragment = new home();
                        break;

                    case R.id.slotsFragments:
                        fragment = new slots();
                        break;

                    case R.id.profileFragments:
                        fragment = new profile();
                        break;


                    case R.id.tretsetsFragments:
                        fragment = new treatset();
                        break;
                }

                loadFragment(fragment);
                return true;


            }


        });
    }


    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}