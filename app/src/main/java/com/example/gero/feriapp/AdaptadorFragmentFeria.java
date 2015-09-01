package com.example.gero.feriapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;



/**
 * Created by Gero on 28/07/2015.
 * edit July92 24/08/2015
 */
public class AdaptadorFragmentFeria extends Fragment {

    //paste your app secret key and consumer key here
    private static final String consumerKey = "9JXCUBeNLUD10bxikM2rOXyn4";
    private static final String consumerSecret = "3ozQNnBHLhyAXyRfuQqryo60Uz0jaPogQVjOoFWbfUDeEJPHVb";


    TextView texto;
    public static AdaptadorFragmentFeria getInstance(int posicion) {
        AdaptadorFragmentFeria adaptador = new AdaptadorFragmentFeria();
        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        adaptador.setArguments(args);

        return adaptador;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_feria, container, false);
        texto = (TextView) layout.findViewById(R.id.textoFeria);
        texto.setText(R.string.slogan);
        Bundle bundle = getArguments();
        if (bundle != null) {
        }
        return layout;
    }



}

