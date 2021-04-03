package com.example.kingdomdeath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;

import java.lang.reflect.Array;
import java.util.*;


public class ArmorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ARMOR = "armor";


    // TODO: Check if we need this - do we need args?
    private int armorPage;

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

    private List<EditText> listEditText;
    private List<CheckBox> listCheckBoxWounds;
    private Map<CheckBox, Boolean> woundMap = new HashMap<>();

    public ArmorFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ArmorFragment newInstance(int i) {
        ArmorFragment fragment = new ArmorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ARMOR, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        armorPage = getArguments().getInt(ARG_ARMOR);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("OnCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_armor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("OnViewCreated");


        Button saveButton2 = view.findViewById(R.id.saveButton2);
        final Button resetButton2 = view.findViewById(R.id.resetButton2);


        //list of EditText where armor-number is input
        this.listEditText = Arrays.asList(
                headEditText = view.findViewById(R.id.headValue),
                armsEditText = view.findViewById(R.id.armsValue),
                bodyEditText = view.findViewById(R.id.bodyValue),
                waistEditText = view.findViewById(R.id.waistValue),
                legsEditText = view.findViewById(R.id.legsValue)
        );

        //list of Checkboxes for light wounds
        this.listCheckBoxWounds = Arrays.asList(
                armsLightCB = (CheckBox) view.findViewById(R.id.armsLightID),
                bodyLightCB = (CheckBox) view.findViewById(R.id.bodyLightID),
                waistLightCB = (CheckBox) view.findViewById(R.id.waistLightID),
                legsLightCB = (CheckBox) view.findViewById(R.id.legsLightID),
                headHeavyCB = (CheckBox) view.findViewById(R.id.headHeavyID),
                armsHeavyCB = (CheckBox) view.findViewById(R.id.armsHeavyID),
                bodyHeavyCB = (CheckBox) view.findViewById(R.id.bodyHeavyID),
                waistHeavyCB = (CheckBox) view.findViewById(R.id.waistHeavyID),
                legsHeavyCB = (CheckBox) view.findViewById(R.id.legsHeavyID
                )
        );

        //Initialized savesavestaed - MODE_PRIVATE = data only accessible to app
        sharedPreferences = this.getActivity().getSharedPreferences(armorpref,
                Context.MODE_PRIVATE);

        woundMap.put(headHeavyCB, this.headHeavyBoolean);
        woundMap.put(armsLightCB, this.armsLightBoolean);
        woundMap.put(armsHeavyCB, this.armsHeavyBoolean);
        woundMap.put(bodyLightCB, this.bodyLightBoolean);
        woundMap.put(bodyHeavyCB, this.bodyHeavyBoolean);
        woundMap.put(waistLightCB, this.waistLightBoolean);
        woundMap.put(waistHeavyCB, this.waistHeavyBoolean);
        woundMap.put(legsLightCB, this.legsLightBoolean);
        woundMap.put(legsHeavyCB, this.legsHeavyBoolean);
        System.out.println("After put: " + headHeavyBoolean);




        load();


        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        resetButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (EditText var : listEditText) {
                    resetValue(var);
                }

                for (CheckBox var : listCheckBoxWounds) {
                    resetCheckBox(var);
                }
            }
        });

       headHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(headHeavyCB);
            }
        });

        armsLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(armsLightCB);
            }
        });

        armsHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(armsHeavyCB);
            }
        });

        bodyLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(bodyLightCB);
            }
        });

        bodyHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(bodyHeavyCB);
            }
        });

        waistLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(waistLightCB);
            }
        });

        waistHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(waistHeavyCB);
            }
        });

        legsLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(legsLightCB);
            }
        });

        legsHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked(legsHeavyCB);
            }
        });




    }

    //method that changes value of boolean put into SharedPreferences in save()-method according to whether the CheckBox is checked or not
    public void isCheckBoxChecked(CheckBox cb){

        for(HashMap.Entry<CheckBox, Boolean> entry : woundMap.entrySet()){
            if(entry.getKey().isChecked()){
                woundMap.put(entry.getKey(), true);
                System.out.println("Yes");
                System.out.println("Checkbox: " + entry.getKey());
                System.out.println("Boolean: " + entry.getValue());
            }

            else {
                woundMap.put(entry.getKey(), false);
                System.out.println("No");
                System.out.println("Checkbox: " + entry.getKey());
                System.out.println("Boolean: " + entry.getValue());
            }

        }

    }

    public void resetValue(EditText et){
        String value = "" + 0;
        et.setText(value);
    }

    public void resetCheckBox(CheckBox cb){
        if(cb.isChecked()){
            cb.setChecked(false);
        }
    }

    //savestate
    //TODO change boolean input.values to get from map

    public void save(){
        System.out.println("Save button pressed");

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


        System.out.println("Save Head H Boolean:" + headHeavyBoolean);
        editor.putBoolean(headHeavy, woundMap.get(headHeavyCB));
        editor.putBoolean(armsLight, woundMap.get(armsLightCB));
        editor.putBoolean(armsHeavy, woundMap.get(armsHeavyCB));
        editor.putBoolean(bodyLight, woundMap.get(bodyLightCB));
        editor.putBoolean(bodyHeavy, woundMap.get(bodyHeavyCB));
        editor.putBoolean(waistLight, woundMap.get(waistLightCB));
        editor.putBoolean(waistHeavy, woundMap.get(waistHeavyCB));
        editor.putBoolean(legsLight, woundMap.get(legsLightCB));
        editor.putBoolean(legsHeavy, woundMap.get(legsHeavyCB));

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

/*
Old reset method:
- for number
headValue = "" + 0;
headEditText.setText(headValue);

- for checkbox
if(headHeavyCB.isChecked()){
headHeavyCB.setChecked(false);

 */