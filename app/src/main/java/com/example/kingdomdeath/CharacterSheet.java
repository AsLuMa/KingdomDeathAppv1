package com.example.kingdomdeath;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CharacterSheet extends AppCompatActivity {

    //window with two tabs where you can switch between attributes and armor
    //CharacterSheet is main activity - armor and attributres are fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet);



    }
}