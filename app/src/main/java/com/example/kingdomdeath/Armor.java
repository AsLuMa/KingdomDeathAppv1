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
    public static final String headValue = "headValue";
    public static final String head = "headValue";

    public static final String armsKey = "armsKey";
    public static final String armsLight = "armsLight";
    public static final String armsHeavy = "armsHeavy";

    public static final String bodyKey = "bodyKey";
    public static final String waistKey = "waistKey";
    public static final String legsKey = "legsKey";

    private EditText armsEditText;
    private String armsValue;

    private CheckBox armsLightCB;
    private boolean armsLightBoolean;

    private CheckBox armsHeavyCB;
    private boolean armsHeavyBoolean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armor);

        armsEditText = (EditText) findViewById(R.id.armsValue);

        ImageButton startMain = findViewById(R.id.startMain);

        Button saveButton2 = findViewById(R.id.saveButton2);
        Button resetButton2 = findViewById(R.id.resetButton2);

        //savestate
        sharedPreferences = getSharedPreferences(armorpref,
                Context.MODE_PRIVATE);

        armsLightCB = (CheckBox) findViewById(R.id.armsLightID);
        armsHeavyCB = (CheckBox) findViewById(R.id.armsHeavyID);

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
                armsValue = "" + 0;
                armsEditText.setText(armsValue);
            }
        });

    }

    public void armsLightCheckbox(View view){
        armsLightBoolean = armsLightCB.isChecked();
    }

    public void armsHeavyCheckbox(View view){
        armsHeavyBoolean = armsHeavyCB.isChecked();
    }



    public void save(){

        armsValue = armsEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!"".equals(armsValue)){
            editor.putInt(armsKey, Integer.parseInt(armsValue));
        }

        editor.putBoolean(armsLight, armsLightBoolean);
        editor.putBoolean(armsHeavy, armsHeavyBoolean);
        editor.commit();

    }


    public void load(){

        if (sharedPreferences.contains(armsKey)) {
            armsEditText.setText("" + sharedPreferences.getInt(armsKey, -1));
        }

        armsLightCB.setChecked(sharedPreferences.getBoolean(armsLight, armsLightBoolean));
        armsHeavyCB.setChecked(sharedPreferences.getBoolean(armsHeavy, armsHeavyBoolean));



    }
}