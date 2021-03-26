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

    //name of file where armor values and checkbox state is stored
    //this save-file is separate from the save-file in MainActivity
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

    //look into gson to make Armor-object (with name of armor-location as key) to put into SharedPreferences
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

        //*editText = view where armor value is entered
        headEditText = (EditText) findViewById(R.id.headValue);
        //*heavy/lightCB = checkbox where you can check whether character has light or heavy wound
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

        //Button that switches to main activity
        ImageButton startMain = findViewById(R.id.startMain);

        Button saveButton2 = findViewById(R.id.saveButton2);
        Button resetButton2 = findViewById(R.id.resetButton2);

        //Initialized savesavestaed - MODE_PRIVATE = data only accessible to app
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
                legsEditText.setText(legsValue);

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

                if(legsLightCB.isChecked()){
                    legsLightCB.setChecked(false);
                }

                if(legsHeavyCB.isChecked()){
                    legsHeavyCB.setChecked(false);
                }

            }
        });

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

    public void bodyLightCheckbox(View view){
        if(bodyLightCB.isChecked()){
            bodyLightCB.setChecked(true);
            bodyLightBoolean = bodyLightCB.isChecked();
        }

        else if(!bodyLightCB.isChecked()){
            bodyLightCB.setChecked(false);
            bodyLightBoolean = armsLightCB.isChecked();
        }

    }

    public void bodyHeavyCheckbox(View view){
        if(bodyHeavyCB.isChecked()){
            bodyHeavyCB.setChecked(true);
            bodyHeavyBoolean = bodyHeavyCB.isChecked();
        }

        else if(!bodyHeavyCB.isChecked()){
            bodyHeavyCB.setChecked(false);
            bodyHeavyBoolean = armsHeavyCB.isChecked();
        }
    }

    public void waistLightCheckbox(View view){
        if(waistLightCB.isChecked()){
            waistLightCB.setChecked(true);
            waistLightBoolean = waistLightCB.isChecked();
        }

        else if(!waistLightCB.isChecked()){
            waistLightCB.setChecked(false);
            waistLightBoolean = waistLightCB.isChecked();
        }

    }

    public void waistHeavyCheckbox(View view){
        if(waistHeavyCB.isChecked()){
            waistHeavyCB.setChecked(true);
            waistHeavyBoolean = armsHeavyCB.isChecked();
        }

        else if(!waistHeavyCB.isChecked()){
            waistHeavyCB.setChecked(false);
            waistHeavyBoolean = armsHeavyCB.isChecked();
        }
    }

    public void legsLightCheckbox(View view){
        if(legsLightCB.isChecked()){
            legsLightCB.setChecked(true);
            legsLightBoolean = legsLightCB.isChecked();
        }

        else if(!legsLightCB.isChecked()){
            legsLightCB.setChecked(false);
            legsLightBoolean = legsLightCB.isChecked();
        }

    }

    public void legsHeavyCheckbox(View view){
        if(legsHeavyCB.isChecked()){
            legsHeavyCB.setChecked(true);
            legsHeavyBoolean = legsHeavyCB.isChecked();
        }

        else if(!legsHeavyCB.isChecked()){
            legsHeavyCB.setChecked(false);
            legsHeavyBoolean = legsHeavyCB.isChecked();
        }
    }


    //savestated doesn't work right with the checkboxes - when you check a box, save, quit, open app, check two more boxes, save quit and open - it seems to toggle buttons in stead of preserve state? check "isChecked() and setChecked(boolean) - how do they work? need to fix several methods


    public void save(){

        //get value from editText-view
        headValue = headEditText.getText().toString();
        armsValue = armsEditText.getText().toString();
        bodyValue = bodyEditText.getText().toString();
        waistValue = waistEditText.getText().toString();
        legsValue = legsEditText.getText().toString();

        //open editor (savestate)
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //save value from editText-field to file
        if (!"".equals(headValue)){
            editor.putInt(headKey, Integer.parseInt(headValue));
        }

        if (!"".equals(armsValue)){
            editor.putInt(armsKey, Integer.parseInt(armsValue));
        }

        if (!"".equals(bodyValue)){
            editor.putInt(bodyKey, Integer.parseInt(bodyValue));
        }

        if (!"".equals(waistValue)){
            editor.putInt(waistKey, Integer.parseInt(waistValue));
        }

        if (!"".equals(legsValue)){
            editor.putInt(legsKey, Integer.parseInt(legsValue));
        }

        editor.putBoolean(headHeavy, headHeavyBoolean);
        editor.putBoolean(armsLight, armsLightBoolean);
        editor.putBoolean(armsHeavy, armsHeavyBoolean);
        editor.putBoolean(bodyLight, bodyLightBoolean);
        editor.putBoolean(bodyHeavy, bodyHeavyBoolean);
        editor.putBoolean(waistLight, waistLightBoolean);
        editor.putBoolean(waistHeavy, waistHeavyBoolean);
        editor.putBoolean(legsLight, legsLightBoolean);
        editor.putBoolean(legsHeavy, legsHeavyBoolean);

        editor.commit();

    }


    public void load(){

        if (sharedPreferences.contains(headKey)) {
            headEditText.setText("" + sharedPreferences.getInt(headKey, -1));
        }

        if (sharedPreferences.contains(armsKey)) {
            armsEditText.setText("" + sharedPreferences.getInt(armsKey, -1));
        }

        if (sharedPreferences.contains(bodyKey)) {
            bodyEditText.setText("" + sharedPreferences.getInt(bodyKey, -1));
        }

        if (sharedPreferences.contains(waistKey)) {
            waistEditText.setText("" + sharedPreferences.getInt(waistKey, -1));
        }

        if (sharedPreferences.contains(legsKey)) {
            legsEditText.setText("" + sharedPreferences.getInt(legsKey, -1));
        }

        headHeavyCB.setChecked(sharedPreferences.getBoolean(headHeavy, headHeavyBoolean));
        armsLightCB.setChecked(sharedPreferences.getBoolean(armsLight, armsLightBoolean));
        armsHeavyCB.setChecked(sharedPreferences.getBoolean(armsHeavy, armsHeavyBoolean));
        bodyLightCB.setChecked(sharedPreferences.getBoolean(bodyLight, bodyLightBoolean));
        bodyHeavyCB.setChecked(sharedPreferences.getBoolean(bodyHeavy, bodyHeavyBoolean));
        waistLightCB.setChecked(sharedPreferences.getBoolean(waistLight, waistLightBoolean));
        waistHeavyCB.setChecked(sharedPreferences.getBoolean(waistHeavy, waistHeavyBoolean));
        legsLightCB.setChecked(sharedPreferences.getBoolean(legsLight, legsLightBoolean));
        legsHeavyCB.setChecked(sharedPreferences.getBoolean(legsHeavy, legsHeavyBoolean));

    }
}