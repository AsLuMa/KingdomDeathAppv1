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


public class MainActivity extends AppCompatActivity {

    private static final String TAG="ActivityState";

    //save state
    private SharedPreferences sharedPreferences;
    //name of file where things are stored
    public static final String mypreference = "mypref";

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

    public void startGUI(){
        survivalValueID = (TextView) findViewById(R.id.survivalValueID);
        insanityValueID = (TextView) findViewById(R.id.insanityValueID);

        ImageButton survivalButtonUp = findViewById(R.id.survivalButtonUp);
        ImageButton insanityButtonUp = findViewById(R.id.insanityButtonUp);
        ImageButton startCharacterSheet = findViewById(R.id.startCharacterSheet);

        ImageButton survivalButtonDown = findViewById(R.id.survivalButtonDown);
        ImageButton insanityButtonDown = findViewById(R.id.insanityButtonDown);

        Button resetButton = findViewById(R.id.resetButton);
        Button saveButton = findViewById(R.id.saveButton);

        sharedPreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        load();

        survivalButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSurvival++;
                survivalValueID.setText("" + currentSurvival);
            }
        });

        insanityButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity++;
                insanityValueID.setText("" + currentInsanity);

            }
        });

        survivalButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSurvival--;
                survivalValueID.setText("" + currentSurvival);

            }
        });

        insanityButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity--;
                insanityValueID.setText("" + currentInsanity);

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSurvival = 0;
                currentInsanity = 0;
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



        startCharacterSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCharacterSheet = new Intent(MainActivity.this, CharacterSheet.class);
                startActivity(startCharacterSheet);

            }
        });

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

        //had to do some shenanigans with '"" + int' in order to set text as a String in textView, while treating the number as an int

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
To do:
- make save/load work
- move save/load to other class
- refactor click methods


* Log.i(TAG, "string");
* Refactor - clean up onStart - refactor into methods
* Save-states
* Tab-layout
* Dynamic listview
*
* onStart
* onResume
* onStop
* onRestart
* onDestroy
* onPause
* onSaveInstanceState(Bundle outState) etc
* onRestoreInstanceState(Bundle savedInstanceState) etc
* */