package com.example.kingdomdeath;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArmorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArmorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ARMOR = "armor";
    //private static final int ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int armorPage;


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_armor, container, false);
    }
}