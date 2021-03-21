package com.example.kingdomdeath;

import android.widget.EditText;
import android.widget.TextView;

public class ArmorSaveState {
    //Class that creates object for one row of the table
    //Object has the following variables (corresponds to views in activity_armor): EditText (save number entered), light wound (boolean), heavy wound (boolean)
    //TextView not included - we do not need to store the state of the TextView
    //Need to make hashmap where key is name of the stat (String)


    //EditText? Store what's in the EditText as number
    private int number;
    private boolean lightWound;
    private boolean heavyWound;

    public ArmorSaveState(int number,boolean lightWound, boolean heavyWound){
        this.number = number;
        this.lightWound = lightWound;
        this.heavyWound = heavyWound;
    }

    //Constructor for head - doesn't have light wound - except it kind of does cause view is invisible (can be always false - won't matter --- might not need this constructor)
    public ArmorSaveState(int number , boolean heavyWound){
        this.number = number;
        this.heavyWound = heavyWound;
    }


}
