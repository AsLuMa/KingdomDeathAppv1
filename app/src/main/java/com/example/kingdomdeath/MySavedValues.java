package com.example.kingdomdeath;

import android.content.SharedPreferences;

//this class does nothing

public class MySavedValues {

    private static int survival;
    private static int insanity;
    private static SharedPreferences pref;


    public static void save(){


        //SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        //pref = getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();



        editor.putInt("survival", survival);
        editor.putInt("insanity", insanity);
        editor.commit();

    }

    public static void load(){

        pref.getInt("key_name", -1);

    }

    public static void reset(){
       // editor.clear();
       // editor.commit();
    }

    public static int getInsanity() {
        return insanity;
    }

    public static int getSurvival() {
        return survival;
    }

    public static void setInsanity(int insanity) {
        MySavedValues.insanity = insanity;
    }

    public static void setSurvival(int survival) {
        MySavedValues.survival = survival;
    }
}

/*getSharedPreferences() : used from within your Activity (or other application Context), to access application-level preferences
Android stores Shared Preferences settings as XML file in shared_prefs folder under DATA/data/{application package} directory. The DATA folder can be obtained by calling Environment.getDataDirectory(
 */