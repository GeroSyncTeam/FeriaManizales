package com.example.gero.feriapp;

/**
 * Created by Gero on 17/08/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapterVisorFotos extends FragmentPagerAdapter {

    private String[] idFotos;


    public static String[] toStringID(int[] idFotos) {
        String[] idFotosS = new String[idFotos.length];
        for (int i = 0; i < idFotos.length; i++) {
            idFotosS[i] = "" + idFotos[i];
        }

        return idFotosS;
    }

    public PageAdapterVisorFotos(FragmentManager fm, int[] idFotos) {
        super(fm);
        this.idFotos = toStringID(idFotos);
    }

    public PageAdapterVisorFotos(FragmentManager fm, String[] idFotos) {
        super(fm);
        this.idFotos = idFotos;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentVisorFotos.getInstance(Integer.parseInt(idFotos[position]));
    }

    @Override
    public int getCount() {
        return idFotos.length;
    }
}