package com.example.gero.feriapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 * Created by Gero on 18/08/2015.
 */
public class CambiarIdioma {

    private Locale myLocale;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String lang;

     public CambiarIdioma(Context context,String lang){
         this.context = context;
         this.lang = lang;
     }

    public void changeLang(SharedPreferences prefs)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        setMyLocale(new Locale(lang));
        saveLocale(prefs);
        Locale.setDefault(getMyLocale());
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = getMyLocale();
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        //updateTexts();
    }

    public void saveLocale(SharedPreferences prefs)
    {
        String langPref = "Lenguaje";
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void loadLocale(SharedPreferences prefs)
    {
        //SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String langPref = "en";
        String language = prefs.getString(langPref, "");
        changeLang(prefs);
    }

    public Locale getMyLocale() {
        return myLocale;
    }

    public void setMyLocale(Locale myLocale) {
        this.myLocale = myLocale;
    }



}
