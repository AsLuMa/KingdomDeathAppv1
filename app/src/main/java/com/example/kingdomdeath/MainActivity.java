package com.example.kingdomdeath;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    //logtag to keep track of app lifecycle (filter in logcat)
    private static final String TAG="ActivityState";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create an adapter that knows which fragment should be shown on each page
        final MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);


        TabLayout tl = findViewById(R.id.tablayout);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ConstraintLayout, adapter.getItem(0))
                .commit();


        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                int position = tab.getPosition();
                getSupportFragmentManager().beginTransaction()
                 .replace(R.id.ConstraintLayout, adapter.getItem(position))
                 .commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


        //starts Armor activity
        /*startArmor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startArmor = new Intent(MainActivity.this, Armor.class);
                startActivity(startArmor);
            }
        });*/


    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
    }
}

/*
TODO:
- Read about non-deprecated way to do this
- Understand tablayout-logic
- FragmentPageAdapter provides a different Fragment for each 'page' (tab)
- ViewPager = slide mellom skjermer - på sikt?
- Refactor Armor-activity into ArmorFragment
- Delete Armor-activity after refactor (all tabs should have same heading - KingdomDeath - means they are in the same activity)
-



- Move Armor to MainActivity - refactor to fragment in MainActivity
- MainActivity - make either tab or bottom nav menu
- All Activities - add menu bar
- Figure out how to save after refactor: same file? SharedPreferences or Database
- New Feature: save all info input as an object, make editable list of characters (SQlite)
- Fix save-state of Armor-class (checkboxes don't work correctly)
- Limit input: survival and insanity shouldn't go below zero; no more than two numbers for armor (crashes on > integer value)
- New branch "Refactor"  - branch icon bottom right bar - press checkout)
*/