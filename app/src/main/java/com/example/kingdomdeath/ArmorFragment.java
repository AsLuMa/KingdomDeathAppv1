package com.example.kingdomdeath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
        //does the below do anything
        args.putInt(ARG_ARMOR, i);
        System.out.println("I:" + i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //does the below do anything
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
                legsHeavyCB = (CheckBox) view.findViewById(R.id.legsHeavyID)
        );

        //Initialized savesavestaed - MODE_PRIVATE = data only accessible to app
        sharedPreferences = this.getActivity().getSharedPreferences(armorpref,
                Context.MODE_PRIVATE);

        //TODO this put()-method should throw nullpointer on first launch, but for some reason doesn't
        //TODO update the following - check for null before putting boolean into map (make seperate method for this, call on all CheckBoxes)
        //if(headHeavyCB == null){
           // headHeavyCB = false;
       // }

        woundMap.put(headHeavyCB, headHeavyBoolean);
        woundMap.put(armsLightCB, armsLightBoolean);
        woundMap.put(armsHeavyCB, armsHeavyBoolean);
        woundMap.put(bodyLightCB, bodyLightBoolean);
        woundMap.put(bodyHeavyCB, bodyHeavyBoolean);
        woundMap.put(waistLightCB, waistLightBoolean);
        woundMap.put(waistHeavyCB, waistHeavyBoolean);
        woundMap.put(legsLightCB, legsLightBoolean);
        woundMap.put(legsHeavyCB, legsHeavyBoolean);
        //System.out.println("After put: " + headHeavyBoolean);

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
                isCheckBoxChecked();
            }

        });

        armsLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();

            }
        });

        armsHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();

            }
        });

        bodyLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();

            }
        });

        bodyHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();

            }
        });

        waistLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();
            }
        });

        waistHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();

            }
        });

        legsLightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();

            }
        });

        legsHeavyCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();

            }
        });

    }

    //method that changes value of boolean put into SharedPreferences in save()-method according to whether the CheckBox is checked or not
    public void isCheckBoxChecked(){

        for(HashMap.Entry<CheckBox, Boolean> entry : woundMap.entrySet()){

        //    if(entry.getKey().equals(null)){
        //        woundMap.put(entry.getKey(), false);
         //       System.out.println("Null");
         //   }
            if(entry.getKey().isChecked()){
                woundMap.put(entry.getKey(), true);
                System.out.println("Yes");
                //System.out.println("Checkbox: " + entry.getKey());
                //System.out.println("Boolean: " + entry.getValue());
            }

            else {
                woundMap.put(entry.getKey(), false);
                System.out.println("No");
                //System.out.println("Checkbox: " + entry.getKey());
                //System.out.println("Boolean: " + entry.getValue());
            }

        }

    }


    public void resetValue(EditText et){
        //removes all values and sets EditText back to hint-state
        et.setText("");

    }

    public void resetCheckBox(CheckBox cb){
        if(cb.isChecked()){
            cb.setChecked(false);

        }
    }

    //saves values from editTexts and state of checkboxes
    public void save(){
        System.out.println("Save button pressed");

        //get value from editText-view
        System.out.println("Save before get from ET: " + headValue);
        headValue = headEditText.getText().toString();
        System.out.println("Save after get from ET: " + headValue);
        armsValue = armsEditText.getText().toString();
        bodyValue = bodyEditText.getText().toString();
        waistValue = waistEditText.getText().toString();
        legsValue = legsEditText.getText().toString();

        //open editor (savestate)
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //save value from editText-field to file
        putEditTextValue(headKey, headValue, editor);
        putEditTextValue(armsKey, armsValue, editor);
        putEditTextValue(bodyKey, bodyValue, editor);
        putEditTextValue(waistKey, waistValue, editor);
        putEditTextValue(legsKey, legsValue, editor);

        //unboxing may produce NullpointerException
        try {
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

            catch(Exception e){
                System.out.println(e);
        }

    }

    public void putEditTextValue(String key, String value, SharedPreferences.Editor e){
        if(!"".equals(value)){
            e.putInt(key, Integer.parseInt(value));
        }

        if(value.equals("")){
            e.putInt(key, 0);
        }
    }


    public void load(){

        loadInt(headKey, headEditText);
        loadInt(armsKey, armsEditText);
        loadInt(bodyKey, bodyEditText);
        loadInt(waistKey, waistEditText);
        loadInt(legsKey, legsEditText);

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

    public void loadInt(String key, EditText et){
        if(sharedPreferences.contains(key)){
            et.setText("" + sharedPreferences.getInt(key, -1));
        }

    }
}

/*
TODO
Number entered in editText:
- Line under input-field needs to wrap around number (max to digits input)
- Grey text-color befor number is input, and after reset (black when number is entered) -
- input: make cursor disappear after number has been input (check out Focus and TextWatcher)
- cursor needs to start to the right of the number?
- call save method on every view - need to set onClick-listener for all editTexts if you do
- write own action listener to shorten code - can we do this with a switch statement?

 */