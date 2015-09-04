package com.example.gero.feriapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.gero.feriapp.Twitter.TimelineActivity;


/**
 * Created by Gero on 28/07/2015.
 * edit July92 24/08/2015
 */
public class AdaptadorFragmentFeria extends Fragment {

    TimelineActivity timelineActivity = new TimelineActivity();
    TextView texto;

    public static AdaptadorFragmentFeria getInstance(int posicion) {
        AdaptadorFragmentFeria adaptador = new AdaptadorFragmentFeria();


        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        adaptador.setArguments(args);

        return adaptador;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.activity_timeline, container, false);
/*
        texto = (TextView) layout.findViewById(R.id.textoFeria);
        texto.setText(R.string.slogan);
        Bundle bundle = getArguments();
        if (bundle != null) {
        }
        */
        return layout;
    }


}

