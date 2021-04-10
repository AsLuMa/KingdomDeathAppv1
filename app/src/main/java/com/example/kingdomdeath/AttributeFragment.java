package com.example.kingdomdeath;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AttributeFragment extends Fragment {

    private SharedPreferences sharedPreferences;

    public static final String attributepref = "attributepref";

    public static final String movementKey = "movementKey";
    public static final String movementBKey1 = "movementBKey1";
    public static final String movementBKey2 = "movementBKey2";

    public static final String accuracyKey = "accuracyKey";
    public static final String accuracyBKey1 = "accuracyBKey1";
    public static final String accuracyBKey2 = "accuracyBKey2";


    public static final String strengthKey = "strengthKey";
    public static final String strengthBKey1 = "strengthBKey1";
    public static final String strengthBKey2 = "strengthBKey2";


    public static final String evasionKey = "evasionKey";
    public static final String evasionBKey1 = "evasionBKey1";
    public static final String evasionBKey2 = "evasionBKey2";


    public static final String luckKey = "luckKey";
    public static final String luckBKey1 = "luckBKey1";
    public static final String luckBKey2 = "luckBKey2";


    public static final String speedKey = "speedKey";
    public static final String speedBKey1 = "speedBKey1";
    public static final String speedBKey2 = "speedBKey2";

    //private AttributeValues movement;



    private EditText movementEditText;
    private String movementValue;
    private CheckBox movementCB1;
    private CheckBox movementCB2;
    private Boolean movementBoolean1;
    private Boolean movementBoolean2;

    private EditText accuracyEditText;
    private String accuracyValue;
    private CheckBox accuracyCB1;
    private CheckBox accuracyCB2;
    private Boolean accuracyBoolean1;
    private Boolean accuracyBoolean2;

    private EditText strengthEditText;
    private String strengthValue;
    private CheckBox strengthCB1;
    private CheckBox strengthCB2;
    private Boolean strengthBoolean1;
    private Boolean strengthBoolean2;

    private EditText evasionEditText;
    private String evasionValue;
    private CheckBox evasionCB1;
    private CheckBox evasionCB2;
    private Boolean evasionBoolean1;
    private Boolean evasionBoolean2;

    private EditText luckEditText;
    private String luckValue;
    private CheckBox luckCB1;
    private CheckBox luckCB2;
    private Boolean luckBoolean1;
    private Boolean luckBoolean2;

    private EditText speedEditText;
    private String speedValue;
    private CheckBox speedCB1;
    private CheckBox speedCB2;
    private Boolean speedBoolean1;
    private Boolean speedBoolean2;

    private List<EditText> listEditText;
    private List<CheckBox> listCheckBox;
    private Map<CheckBox, Boolean> checkBoxBooleanMap = new HashMap<>();




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttributeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AttributeFragment newInstance(String param1, String param2) {
        AttributeFragment fragment = new AttributeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("OnCreateViewAttributeFragment");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attribute, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("OnViewCreateAttributeFragment");

        final Button saveButton2 = view.findViewById(R.id.saveButton2);
        final Button resetButton2 = view.findViewById(R.id.resetButton2);

        this.listEditText = Arrays.asList(
                movementEditText = view.findViewById(R.id.movementValue)
        );

        this.listCheckBox = Arrays.asList(
                movementCB1 = (CheckBox) view.findViewById(R.id.movementCB1),
                movementCB2 = (CheckBox) view.findViewById(R.id.movementCB2),
                accuracyCB1 = (CheckBox) view.findViewById(R.id.accuracyCB1),
                accuracyCB2 = (CheckBox) view.findViewById(R.id.accuracyCB2),
                strengthCB1 = (CheckBox) view.findViewById(R.id.strengthCB1),
                strengthCB2 = (CheckBox) view.findViewById(R.id.strengthCB2),
                evasionCB1 = (CheckBox) view.findViewById(R.id.evasionCB1),
                evasionCB2 = (CheckBox) view.findViewById(R.id.evasionCB2),
                luckCB1 = (CheckBox) view.findViewById(R.id.luckCB1),
                luckCB2 = (CheckBox) view.findViewById(R.id.luckCB2),
                speedCB1 = (CheckBox) view.findViewById(R.id.speedCB1),
                speedCB2 = (CheckBox) view.findViewById(R.id.speedCB2)
        );

        sharedPreferences = this.getActivity().getSharedPreferences(attributepref,
                Context.MODE_PRIVATE);

        if(movementBoolean1 == null){
            movementBoolean1 = false;
        }
        if(movementBoolean2 == null){
            movementBoolean2 = false;
        }


        checkBoxBooleanMap.put(movementCB1, movementBoolean1);
        checkBoxBooleanMap.put(movementCB2, movementBoolean2);

        load();

        saveButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Savebutton onclick");
                save();
            }
        });

        resetButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (EditText var : listEditText) {
                    resetValue(var);
                }

                for (CheckBox var : listCheckBox) {
                    resetCheckBox(var);
                }

            }

        });

        movementCB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();
            }

        });

        movementCB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBoxChecked();
            }

        });

    }



    public void isCheckBoxChecked(){

        for(HashMap.Entry<CheckBox, Boolean> entry : checkBoxBooleanMap.entrySet()){

            if(entry.getKey().equals(null)){
                checkBoxBooleanMap.put(entry.getKey(), false);
                System.out.println("Null");
            }
            if(entry.getKey().isChecked()){
                checkBoxBooleanMap.put(entry.getKey(), true);
                System.out.println("Yes");
                //System.out.println("Checkbox: " + entry.getKey());
                //System.out.println("Boolean: " + entry.getValue());
            }

            else {
                checkBoxBooleanMap.put(entry.getKey(), false);
                System.out.println("No");
                //System.out.println("Checkbox: " + entry.getKey());
                //System.out.println("Boolean: " + entry.getValue());
            }

        }

    }


    public void resetValue(EditText et){
        System.out.println("Reset Edit Text");
        //removes all values and sets EditText back to hint-state
        et.setText("");

    }

    public void resetCheckBox(CheckBox cb){
        System.out.println("Reset Checkbox");
        if(cb.isChecked()){
            cb.setChecked(false);

        }
    }

    //saves values from editTexts and state of checkboxes
    public void save(){
        System.out.println("Save button pressed");

        //String connectionsJSONString = new Gson().toJson();


    //    this.movement = new AttributeValues(movementEditText.getText().toString(), movementEditText, movementCB1, movementCB2, movementBoolean1, movementBoolean2);


        //get value from editText-view
        movementValue = movementEditText.getText().toString();

        //open editor (savestate)
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Gson gson = new Gson();
        //String json = gson.toJson(this.movement);
        //editor.putString(movementKey, json);
       // editor.commit();

        //save value from editText-field to file
        putEditTextValue(movementKey, movementValue, editor);

        //unboxing may produce NullpointerException
        try {
            editor.putBoolean(movementBKey1, checkBoxBooleanMap.get(movementCB1));
            editor.putBoolean(movementBKey2, checkBoxBooleanMap.get(movementCB2));

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
        System.out.println("LoadAttributeFragment");

        /*Gson gson = new Gson();
        String json = sharedPreferences.getString("MyObject", "");
        this.movement = gson.fromJson(json, AttributeValues.class);*/

        loadInt(movementKey, movementEditText);
        System.out.println("LoadInt");

        try {

            movementCB1.setChecked(sharedPreferences.getBoolean(movementBKey1, movementBoolean1));
            movementCB2.setChecked(sharedPreferences.getBoolean(movementBKey2, movementBoolean2));
        }

        catch (Exception e){
            System.out.println(e);

        }

        System.out.println("EndOFLoad");

    }

    public void loadInt(String key, EditText et){
        if(sharedPreferences.contains(key)){
            et.setText("" + sharedPreferences.getInt(key, -1));
        }

    }
}