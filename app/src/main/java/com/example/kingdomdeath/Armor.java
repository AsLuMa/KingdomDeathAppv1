package com.example.kingdomdeath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Armor extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    //name of file where things are stored
    public static final String armorpref = "armorpref";

    //keys to find associated value (savestate)
    public static final String headKey = "headKey";
    public static final String headHeavy = "headValue";

    public static final String armsKey = "armsKey";
    public static final String armsLight = "armsLight";
    public static final String armsHeavy = "armsHeavy";

    public static final String bodyKey = "bodyKey";
    public static final String bodyLight = "bodyLight";
    public static final String bodyHeavy = "bodyHeavy";

    public static final String waistKey = "waistKey";
    public static final String waistLight = "waitsLight";
    public static final String waistHeavy = "waistHeavy";

    public static final String legsKey = "legsKey";
    public static final String legsLight = "legsLight";
    public static final String legsHeavy = "legsHeavy";

    //tried to make Armor-object to put into SharedPreferences, but need to download gson-something to do that
    //refactor to database storage/sqlite

    //head
    private EditText headEditText;
    private String headValue;
    private CheckBox headHeavyCB;
    private boolean headHeavyBoolean;

    //arms
    private EditText armsEditText;
    private String armsValue;
    private CheckBox armsLightCB;
    private boolean armsLightBoolean;
    private CheckBox armsHeavyCB;
    private boolean armsHeavyBoolean;

    //body
    private EditText bodyEditText;
    private String bodyValue;
    private CheckBox bodyLightCB;
    private boolean bodyLightBoolean;
    private CheckBox bodyHeavyCB;
    private boolean bodyHeavyBoolean;


    //waist
    private EditText waistEditText;
    private String waistValue;
    private CheckBox waistLightCB;
    private boolean waistLightBoolean;
    private CheckBox waistHeavyCB;
    private boolean waistHeavyBoolean;


    //legs
    private EditText legsEditText;
    private String legsValue;
    private CheckBox legsLightCB;
    private boolean legsLightBoolean;
    private CheckBox legsHeavyCB;
    private boolean legsHeavyBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armor);

        headEditText = (EditText) findViewById(R.id.headValue);
        headHeavyCB = (CheckBox) findViewById(R.id.headHeavyID);

        armsEditText = (EditText) findViewById(R.id.armsValue);
        armsLightCB = (CheckBox) findViewById(R.id.armsLightID);
        armsHeavyCB = (CheckBox) findViewById(R.id.armsHeavyID);

        //body
        bodyEditText = (EditText) findViewById(R.id.bodyValue);
        bodyLightCB = (CheckBox) findViewById(R.id.bodyLightID);
        bodyHeavyCB = (CheckBox) findViewById(R.id.bodyHeavyID);

        //waist
        waistEditText = (EditText) findViewById(R.id.waistValue);
        waistLightCB = (CheckBox) findViewById(R.id.waistLightID);
        waistHeavyCB = (CheckBox) findViewById(R.id.waistHeavyID);

        //legs
        legsEditText = (EditText) findViewById(R.id.legsValue);
        legsLightCB = (CheckBox) findViewById(R.id.legsLightID);
        legsHeavyCB = (CheckBox) findViewById(R.id.legsHeavyID);

        ImageButton startMain = findViewById(R.id.startMain);

        Button saveButton2 = findViewById(R.id.saveButton2);
        Button resetButton2 = findViewById(R.id.resetButton2);

        //savestate
        sharedPreferences = getSharedPreferences(armorpref,
                Context.MODE_PRIVATE);

        load();


        startMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(Armor.this, MainActivity.class);
                startActivity(startMain);
            }
        });


        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        resetButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headValue = "" + 0;
                headEditText.setText(headValue);

                armsValue = "" + 0;
                armsEditText.setText(armsValue);

                bodyValue = "" + 0;
                bodyEditText.setText(bodyValue);

                waistValue = "" + 0;
                waistEditText.setText(waistValue);

                legsValue = "" + 0;
                legsEditText.setText(armsValue);

                if(headHeavyCB.isChecked()){
                    headHeavyCB.setChecked(false);
                }

                if(armsLightCB.isChecked()){
                    armsLightCB.setChecked(false);
                }

                if(armsHeavyCB.isChecked()){
                    armsHeavyCB.setChecked(false);
                }

                if(bodyLightCB.isChecked()){
                    bodyLightCB.setChecked(false);
                }

                if(bodyHeavyCB.isChecked()){
                    bodyHeavyCB.setChecked(false);
                }

                if(waistLightCB.isChecked()){
                    waistLightCB.setChecked(false);
                }

                if(waistHeavyCB.isChecked()){
                    waistHeavyCB.setChecked(false);
                }

                if(armsLightCB.isChecked()){
                    armsLightCB.setChecked(false);
                }

                if(armsHeavyCB.isChecked()){
                    armsHeavyCB.setChecked(false);
                }



            }
        });

    }

    public void armsLightCheckbox(View view){
        if(armsLightCB.isChecked()){
            armsLightCB.setChecked(true);
            armsLightBoolean = armsLightCB.isChecked();
        }

        else if(!armsLightCB.isChecked()){
            armsLightCB.setChecked(false);
            armsLightBoolean = armsLightCB.isChecked();
        }

    }

    public void armsHeavyCheckbox(View view){
        if(armsHeavyCB.isChecked()){
            armsHeavyCB.setChecked(true);
            armsHeavyBoolean = armsHeavyCB.isChecked();
        }

        else if(!armsHeavyCB.isChecked()){
            armsHeavyCB.setChecked(false);
            armsHeavyBoolean = armsHeavyCB.isChecked();
        }
    }

    public void headHeavyCheckbox(View view){
        if(headHeavyCB.isChecked()){
            headHeavyCB.setChecked(true);
            headHeavyBoolean = headHeavyCB.isChecked();
        }

        else if(!headHeavyCB.isChecked()){
            headHeavyCB.setChecked(false);
            headHeavyBoolean = headHeavyCB.isChecked();
        }

    }


    public void save(){

        armsValue = armsEditText.getText().toString();
        headValue = headEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!"".equals(armsValue)){
            editor.putInt(armsKey, Integer.parseInt(armsValue));
        }

        if (!"".equals(headValue)){
            editor.putInt(headKey, Integer.parseInt(headValue));
        }

        editor.putBoolean(armsLight, armsLightBoolean);
        editor.putBoolean(armsHeavy, armsHeavyBoolean);
        editor.putBoolean(headHeavy, headHeavyBoolean);
        editor.commit();



    }


    public void load(){

        if (sharedPreferences.contains(armsKey)) {
            armsEditText.setText("" + sharedPreferences.getInt(armsKey, -1));
        }

        if (sharedPreferences.contains(headKey)) {
            headEditText.setText("" + sharedPreferences.getInt(headKey, -1));
        }


        armsLightCB.setChecked(sharedPreferences.getBoolean(armsLight, armsLightBoolean));
        armsHeavyCB.setChecked(sharedPreferences.getBoolean(armsHeavy, armsHeavyBoolean));

        headHeavyCB.setChecked(sharedPreferences.getBoolean(headHeavy, headHeavyBoolean));

    }
}