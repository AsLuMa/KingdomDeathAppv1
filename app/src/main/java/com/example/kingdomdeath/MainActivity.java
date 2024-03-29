package com.example.kingdomdeath;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public String printAttributeValue(AttributeValues av){
        return "Value: " + av.getAttributeValue() + " FA: " + av.getFA() + " Token: " + av.getToken();
    }


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
- Read about fragments (FragmentPageAdapter is deprecated)
- Understand tablayout-logic (specifically passing of arguments and bundles)

- Delete Armor-activity after refactor

- Add menu bar to MainActivity
- Make flippable
- ViewPager2 = slide mellom skjermer - på sikt?

Saving info:
- SharedPreferences - best practice (currently using one file per tab)
- Research other ways to store data in tabs
- want last opened tab to be the tab loaded onResume()
- OnTabUnselected() and onTabReselected()?
*/