package com.example.gero.feriapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FragmentVisorFotos extends Fragment {

    private ImageView fotoVisorFotos;
    private int[] idFotosHRD;
    private boolean tipo; // sirve para verificar si agrega el evento click
    private int position;

    public static FragmentVisorFotos getInstance(int idFoto, boolean tipo, int[] idFotosHRD, int position) {

        FragmentVisorFotos adaptador = new FragmentVisorFotos();
        Bundle args = new Bundle();
        args.putInt("ID_FOTO", idFoto);
        adaptador.setTipo(tipo);
        adaptador.setIdFotosHRD(idFotosHRD);
        adaptador.position = position;
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
        if (tipo) {
            fotoVisorFotos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("EVENTO CLICK", "SE DIO CLICK");
                    openDialog();
                }
            });
        }
        return view;
    }

    /**
     * Despliega el FragmentDialogo en el que se visualizan las fotos de la candidata
     *
     */
    public void openDialog() {
        //c?digo del dialog que muestra las fotos
        fragmentDialogo overlay = fragmentDialogo.getInstance(getIdFotosHRD());
        overlay.setPosicionViewPager(position);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        overlay.show(fm, "FragmentDialog");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int[] getIdFotosHRD() {
        return idFotosHRD;
    }

    public void setIdFotosHRD(int[] idFotosHRD) {
        this.idFotosHRD = idFotosHRD;
    }
}
