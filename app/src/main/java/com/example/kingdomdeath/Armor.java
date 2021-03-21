package com.example.kingdomdeath;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Armor extends AppCompatActivity {

    //Fook the attributes!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armor);

        ImageButton startMain = findViewById(R.id.startMain);


        startMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(Armor.this, MainActivity.class);
                startActivity(startMain);
            }
        });








    }
}