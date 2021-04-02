package com.example.kingdomdeath;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SurvivalFragment extends Fragment {

    // TODO: Figure out what this does
    // the fragment initialization parameters
    private static final String ARG_SURVIVAL = "survival";


    // TODO: Figure out what this does (if anything)
    private int survivalPage;


    //save state
    private SharedPreferences sharedPreferences;
    //name of file where things are stored
    public static final String mypreference = "mypref";

    //keys to find associated value (savestate)
    public static final String survival = "survivalKey";
    public static final String insanity = "insanityKey";

    private int currentSurvival;
    private int currentInsanity;

    private TextView survivalValueID;
    private TextView insanityValueID;


    public SurvivalFragment() {
        // Required empty public constructor
    }


    // TODO: Do I need this int? Does it do anything?
    public static SurvivalFragment newInstance(int i) {
        SurvivalFragment fragment = new SurvivalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SURVIVAL, i);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        survivalPage = getArguments().getInt(ARG_SURVIVAL);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survival, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //view.findViewById().setOnClickListener();

        survivalValueID = (TextView) view.findViewById(R.id.survivalValueID);
        insanityValueID = (TextView) view.findViewById(R.id.insanityValueID);

        ImageButton survivalButtonUp = view.findViewById(R.id.survivalButtonUp);
        ImageButton insanityButtonUp = view.findViewById(R.id.insanityButtonUp);

        //ImageButton startArmor = findViewById(R.id.startArmor);

        ImageButton survivalButtonDown = view.findViewById(R.id.survivalButtonDown);
        ImageButton insanityButtonDown = view.findViewById(R.id.insanityButtonDown);

        Button resetButton = view.findViewById(R.id.resetButton);
        Button saveButton = view.findViewById(R.id.saveButton);

        //savestate
        sharedPreferences = this.getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        load();

        survivalButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSurvival++;
                updateSurvival(survivalValueID);
            }
        });

        insanityButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity++;
                updateInsanity(insanityValueID);
            }
        });

        survivalButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSurvival--;
                updateSurvival(survivalValueID);
            }
        });

        insanityButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity--;
                updateInsanity(insanityValueID);
            }
        });


        //resets survival and insanity values
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInsanity = 0;
                currentSurvival = 0;
                survivalValueID.setText("" + currentSurvival);
                insanityValueID.setText("" + currentInsanity);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void updateSurvival(TextView view){
        view.setText("" + currentSurvival);
    }

    public void updateInsanity(TextView view){
        view.setText("" + currentInsanity);
    }


    public void save() {
        String s = survivalValueID.getText().toString();
        String i = insanityValueID.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(survival, Integer.parseInt(s));
        editor.putInt(insanity, Integer.parseInt(i));
        editor.commit();

    }

    public void load() {
        //use  '"" + int' in order to set text as a String in textView - and int can be treated as int

        if (sharedPreferences.contains(survival)) {
            survivalValueID.setText("" + sharedPreferences.getInt(survival, -1));
        }
        if (sharedPreferences.contains(insanity)) {
            insanityValueID.setText("" + sharedPreferences.getInt(insanity, -1));
        }


    }


}

/*

//TODO Alt-method from Udacity - do we need to attach to root or not?
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                    Bundle savedInstanceState) {
   View rootView = inflater.inflate(R.layout.word_list, container, false);

   return rootView;
           }
 */

/*XML for skull button, in case of emergency:
        <ImageButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@drawable/skull"
                android:id="@+id/startArmor"
                android:background="#000000"
                android:padding="1dp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toTopOf="@+id/tableLayout" app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"/>
 */