package com.example.kingdomdeath;

import android.app.FragmentTransaction;
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
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabItem;


public class MainActivity extends AppCompatActivity {

    //logtag to keep track of app lifecycle (filter in logcat)
    private static final String TAG="ActivityState";

    //save state
    private SharedPreferences sharedPreferences;
    //name of file where things are stored
    public static final String mypreference = "mypref";

    //keys to find associated value (savestate)
    public static final String survival = "survivalKey";
    public static final String insanity = "insanityKey";

    private int currentSurvival;
    private int currentInsanity;

    private TextView survivalValueID;
    private TextView insanityValueID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGUI();

        Log.i(TAG, "onCreate");

    }

    //makes GUI + load saved values + onclick listener for buttons
    //refactor buttons to onclick(View view)-methods through designer?
    public void startGUI(){
        survivalValueID = (TextView) findViewById(R.id.survivalValueID);
        insanityValueID = (TextView) findViewById(R.id.insanityValueID);

        ImageButton survivalButtonUp = findViewById(R.id.survivalButtonUp);
        ImageButton insanityButtonUp = findViewById(R.id.insanityButtonUp);

        ImageButton startArmor = findViewById(R.id.startArmor);

        ImageButton survivalButtonDown = findViewById(R.id.survivalButtonDown);
        ImageButton insanityButtonDown = findViewById(R.id.insanityButtonDown);

        Button resetButton = findViewById(R.id.resetButton);
        Button saveButton = findViewById(R.id.saveButton);

        //savestate
        sharedPreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        load();

        survivalButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSurvival++;
                updateSurvival(survivalValueID);
            }
        });

        insanityButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity++;
                updateInsanity(insanityValueID);
            }
        });

        survivalButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSurvival--;
                updateSurvival(survivalValueID);
            }
        });

        insanityButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity--;
                updateInsanity(insanityValueID);
            }
        });


        //resets survival and insanity values
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity = 0;
                currentSurvival = 0;
                survivalValueID.setText("" + currentSurvival);
                insanityValueID.setText("" + currentInsanity);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        //starts Armor activity
        startArmor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startArmor = new Intent(MainActivity.this, Armor.class);
                startActivity(startArmor);
            }
        });



    }





    public void updateSurvival(TextView view){
        view.setText("" + currentSurvival);
    }

    public void updateInsanity(TextView view){
        view.setText("" + currentInsanity);
    }


    public void save() {
        String s = survivalValueID.getText().toString();
        String i = insanityValueID.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(survival, Integer.parseInt(s));
        editor.putInt(insanity, Integer.parseInt(i));
        editor.commit();

        Log.i(TAG, "save");
    }

    public void load() {

        //sharedPreferences = getSharedPreferences(mypreference,
          //      Context.MODE_PRIVATE);

        //use  '"" + int' in order to set text as a String in textView - and int can be treated as int

        if (sharedPreferences.contains(survival)) {
            survivalValueID.setText("" + sharedPreferences.getInt(survival, -1));
        }
        if (sharedPreferences.contains(insanity)) {
            insanityValueID.setText("" + sharedPreferences.getInt(insanity, -1));
        }

        Log.i(TAG, "load");

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
- Move Armor to MainActivity - refactor to fragment in MainActivity
- MainActivity - make either tab or bottom nav menu
- All Activities - add menu bar
- Figure out how to save after refactor: same file? SharedPreferences or Database
- New Feature: save all info input as an object, make editable list of characters (SQlite)
- Fix save-state of Armor-class (checkboxes don't work correctly)
- Limit input: survival and insanity shouldn't go below zero; no more than two numbers for armor (crashes on > integer value)
- New branch "Refactor"  - branch icon bottom right bar - press checkout)
*/