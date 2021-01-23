package com.example.bookie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Sampler;

public class Preference {

    private static SharedPreferences sPref;
    private static final String DEFAULT_PREF="Default Pref";
    private static final String KEY_SELECTED_FILTER = "Selected Filter";
    private static Preference sInstance;

    private Preference(){}

    public static synchronized Preference getInstance(Context context){
        if(sInstance==null){
            sInstance = new Preference();
        sPref = context.getSharedPreferences(DEFAULT_PREF,Context.MODE_PRIVATE);
        }
            return sInstance;
        }

        public void setSelectedFilter(String filter){       // set or update shared preference value
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(KEY_SELECTED_FILTER, filter);
        editor.apply();
        }

        public String getSelectedFilter(){      // read preference value
            return sPref.getString(KEY_SELECTED_FILTER,null);
        }
}
