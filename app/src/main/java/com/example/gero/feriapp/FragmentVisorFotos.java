package com.example.gero.feriapp;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FragmentVisorFotos extends Fragment {

   private ImageView fotoVisorFotos;

    public static FragmentVisorFotos getInstance(int idFoto){

        FragmentVisorFotos adaptador = new FragmentVisorFotos();
        Bundle args = new Bundle();
        args.putInt("ID_FOTO", idFoto);
        adaptador.setArguments(args);
        return adaptador;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* Drawable d = getResources().getDrawable(R.drawable.yourimage);
        int h = d.getIntrinsicHeight();
        int w = d.getIntrinsicWidth();
     */
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visor_fotos, container, false);
        //view.setBackgroundResource((Integer) getArguments().get("ID_FOTO"));
        //view.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        fotoVisorFotos = (ImageView) view.findViewById(R.id.imagenVisorFotos);
        fotoVisorFotos.setImageResource((Integer) getArguments().get("ID_FOTO"));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
