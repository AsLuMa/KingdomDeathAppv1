package com.example.kingdomdeath;

import android.widget.CheckBox;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class AttributeValues {

    private String name;
    private EditText et;
    private Map<CheckBox, Boolean> checkBoxBooleanMap;
    private CheckBox cb1;
    private CheckBox cb2;
    private Boolean b1;
    private Boolean b2;

    public AttributeValues(String name, EditText value, CheckBox cb1, CheckBox cb2, Boolean b1, Boolean b2){
        this.name = name;
        this.et = value;
        this.b1 = false;
        this.b2 = false;
        this.checkBoxBooleanMap = new HashMap<>();
        putIntoMap(cb1, b1);
        putIntoMap(cb2, b2);
    }

    public void putIntoMap(CheckBox cb, Boolean bl){
        checkBoxBooleanMap.put(cb, bl);
    }



}
