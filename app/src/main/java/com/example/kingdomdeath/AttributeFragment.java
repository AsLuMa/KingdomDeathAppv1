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
import com.google.gson.Gson;

import java.util.*;


public class AttributeFragment extends Fragment {

    public static final String attributepref = "attributepref";

    public static final String movementKey = "movementKey";
    public static final String accuracyKey = "accuracyKey";
    public static final String strengthKey = "strengthKey";
    public static final String evasionKey = "evasionKey";
    public static final String luckKey = "luckKey";
    public static final String speedKey = "speedKey";


    private SharedPreferences sharedPreferences;

    private EditText movementEditText;
    private CheckBox movementCB1;
    private CheckBox movementCB2;

    private EditText accuracyEditText;
    private CheckBox accuracyCB1;
    private CheckBox accuracyCB2;

    private EditText strengthEditText;
    private CheckBox strengthCB1;
    private CheckBox strengthCB2;

    private EditText evasionEditText;
    private CheckBox evasionCB1;
    private CheckBox evasionCB2;

    private EditText luckEditText;
    private CheckBox luckCB1;
    private CheckBox luckCB2;

    private EditText speedEditText;
    private CheckBox speedCB1;
    private CheckBox speedCB2;

    AttributeValues movement;
    AttributeValues accuracy;
    AttributeValues strength;
    AttributeValues evasion;
    AttributeValues luck;
    AttributeValues speed;

    private List<EditText> listEditText;
    private List<CheckBox> listCheckBox;
    private Map<CheckBox, Boolean> checkBoxMap = new HashMap<>();


    public AttributeFragment() {
        // Required empty public constructor

    }

    public enum AttributeName{
        MOVEMENT, ACCURACY, STRENGTH, EVASION, LUCK, SPEED
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        sharedPreferences = this.getActivity().getSharedPreferences(attributepref,
                Context.MODE_PRIVATE);

        
        this.listEditText = Arrays.asList(
                movementEditText = view.<EditText>findViewById(R.id.movementValue),
                accuracyEditText = view.<EditText>findViewById(R.id.accuracyValue),
                strengthEditText = view.<EditText>findViewById(R.id.strengthValue),
                evasionEditText = view.<EditText>findViewById(R.id.evasionValue),
                luckEditText = view.<EditText>findViewById(R.id.luckValue),
                speedEditText = view.<EditText>findViewById(R.id.speedValue)
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


        try {
            loadValuesToUIComponents();
        }

        catch (Exception e){
            System.out.println(e);

        }



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

        saveButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //System.out.println("Savebutton onclick");
                save();
            }
        });

        //buttonlisteners -- checkboxes
        //listeners for the buttons not needed, since the saving is done on click of the save button and the switch-statment in the saveFROM-method handles checking the state of the checkbox


    }

    public void save(){
        System.out.println("Save button pressed");


        //if-statement to check for null, so a new object doesn't get created if one already exists
        if(this.movement == null){
            this.movement = new AttributeValues("", false, false);
            System.out.println("OBJECT IS CREATED IN save " + printAttributeValue(this.movement));
        }

        if(this.accuracy == null){
            this.accuracy = new AttributeValues("", false, false);
            System.out.println("OBJECT IS CREATED IN save " + printAttributeValue(this.accuracy));
        }

        if(this.strength == null){
            this.strength = new AttributeValues("", false, false);
            System.out.println("OBJECT IS CREATED IN save " + printAttributeValue(this.strength));
        }

        if(this.evasion == null){
            this.evasion = new AttributeValues("", false, false);
            System.out.println("OBJECT IS CREATED IN save " + printAttributeValue(this.evasion));
        }

        if(this.luck == null){
            this.luck = new AttributeValues("", false, false);
            System.out.println("OBJECT IS CREATED IN save " + printAttributeValue(this.luck));
        }

        if(this.speed == null){
            this.speed = new AttributeValues("", false, false);
            System.out.println("OBJECT IS CREATED IN save " + printAttributeValue(this.speed));
        }






        saveValuesFROMUIComponents();

        System.out.println("Movement after saveSwitch has been called" + printAttributeValue(this.movement));

        SharedPreferences.Editor editor = sharedPreferences.edit();

        try {

        Gson mgson = new Gson();
        Gson agson = new Gson();
        String mjson = mgson.toJson(this.movement);
        String ajson = agson.toJson(this.accuracy);
        
        editor.putString(movementKey, mjson);
        editor.putString(accuracyKey, ajson);
        
        System.out.println(mjson + ": movementjson");
        System.out.println(ajson + ": accuracyjson");
        
        editor.commit();
        }

        catch(Exception e){
            System.out.println(e);
        }

    }


    public void resetValue(EditText et){
        //removes all values and sets EditText back to hint-state
        et.setText("");

    }
    
    public void resetCheckBox(CheckBox cb){
            cb.setChecked(false);
    }


    //returns null before hitting the save button on reload, but save function still works - same happens in ArmorFragment
    //get value from edittext - put into relevant box
    public void saveValuesFROMUIComponents(){
        System.out.println("SaveFROMUI");

        for (AttributeName attributes : AttributeName.values())

        switch(attributes){
            case MOVEMENT:
                System.out.println("- SAVESWITCH: Movement before update" + printAttributeValue(this.movement));
                updateStringValue(movementEditText, this.movement);
                updateFA(movementCB1, this.movement);
                updateToken(movementCB2, this.movement);
                System.out.println("- SAVESWITCH: Movement after update" + printAttributeValue(this.movement));
                break;

            case ACCURACY:
                System.out.println("- SAVESWITCH: Accuracy before update" + printAttributeValue(this.accuracy));
                updateStringValue(accuracyEditText, this.accuracy);
                updateFA(accuracyCB1, this.accuracy);
                updateToken(accuracyCB2, this.accuracy);
                System.out.println("- SAVESWITCH: Accuracy after update" + printAttributeValue(this.accuracy));

                break;


            case STRENGTH:
            case EVASION:
            case LUCK:
            case SPEED:
            default:
                System.out.println("Switch default in saveValuesFROMUIComponents");
        }

    }

    //update values of AttributeValues-object with current values from UI
    public String updateStringValue(EditText et, AttributeValues av){
        String value = et.getText().toString();
        System.out.println("UpdateStringValue " + value);
        av.setAttributeValue(value);
        return value;
    }

    public boolean updateFA(CheckBox cb, AttributeValues av){
        Boolean bl = cb.isChecked();
        System.out.println("Update FA Value " + bl);
        av.setFA(bl);
        return bl;
    }

    public boolean updateToken(CheckBox cb, AttributeValues av){
        Boolean bl = cb.isChecked();
        System.out.println("Update FA Value " + bl);
        av.setToken(bl);
        return bl;
    }


    public void loadValuesToUIComponents(){
        System.out.println("Load values");

        for (AttributeName attributes : AttributeName.values())


            switch(attributes){
                case MOVEMENT:
                    System.out.println("Switch movement load");
                    Gson mgson = new Gson();
                    String mjson = sharedPreferences.getString(movementKey, "");
                    this.movement = mgson.fromJson(mjson, AttributeValues.class);
                    movementEditText.setText("" + this.movement.getAttributeValue());
                    movementCB1.setChecked(this.movement.getFA());
                    movementCB2.setChecked(this.movement.getToken());
                    break;
                case ACCURACY:
                    System.out.println("Switch accuracy load");
                    Gson agson = new Gson();
                    String ajson = sharedPreferences.getString(accuracyKey, "");
                    this.accuracy = agson.fromJson(ajson, AttributeValues.class);
                    accuracyEditText.setText("" + this.accuracy.getAttributeValue());
                    accuracyCB1.setChecked(this.accuracy.getFA());
                    accuracyCB2.setChecked(this.accuracy.getToken());
                    break;
                    
                case STRENGTH:
                case EVASION:
                case LUCK:
                case SPEED:
                default:
                    System.out.println("Switch default in loadValuesFROMUIComponents");
            }

    }

    public void updateUI(){

    }

    /*                    Gson gson = new Gson();
                    String json = sharedPreferences.getString(movementKey, "");
                    this.movement = gson.fromJson(json, AttributeValues.class);
                    movementEditText.setText("" + this.movement.getAttributeValue());
                    System.out.println("Switch movement load");
                    movementCB1.setChecked(this.movement.getFA());
                    movementCB2.setChecked(this.movement.getToken());*/

    public void isCheckBoxChecked(){




      /*  for(HashMap.Entry<CheckBox, Boolean> entry : checkBoxMap.entrySet()){

            if(entry.getKey().isChecked()){
                checkBoxMap.put(entry.getKey(), true);
                //set value in object to true
                System.out.println("Yes");
            }

            else {
                checkBoxMap.put(entry.getKey(), false);
                System.out.println("No");
            }

        }*/

    }

    public String printAttributeValue(AttributeValues av){
        return "Name: " + av.getNameOfAttribute() + " Value: " + av.getAttributeValue() + " FA: " + av.getFA() + " Token: " + av.getToken();
    }


}
