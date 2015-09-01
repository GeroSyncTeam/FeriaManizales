package com.example.gero.feriapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.gero.feriapp.recyclerView.AdaptadorRVD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JULY92 on 21/08/2015.
 */
public class AdaptadorFragmentDias extends Fragment {

    private RecyclerView listaDias;
    private AdaptadorRVD adaptadorRVD;


    public static AdaptadorFragmentProgramacion getInstance(int posicion) {
        AdaptadorFragmentProgramacion adaptador = new AdaptadorFragmentProgramacion();
        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        adaptador.setArguments(args);

        return adaptador;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_dias, container, false);
        //recyclerView ---------------------------------------
        listaDias = (RecyclerView) layout.findViewById(R.id.recyclerView);
        adaptadorRVD = new AdaptadorRVD(getActivity(), getDatosDias());
        listaDias.setAdapter(adaptadorRVD);
        listaDias.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }


    public static List<informacionDias> getDatosDias() {
        List<informacionDias> listaIP = new ArrayList<>();
        int[] idFondos = {R.drawable.header, R.drawable.header, R.drawable.header, R.drawable.header};
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves"};
        String[] fechas = {"Primero", "Segundo", "Tercero", "Cuarto"};
        for (int i = 0; i < idFondos.length; i++) {
            informacionDias aux = new informacionDias();
            aux.setDia(dias[i]);
            aux.setFecha(fechas[i]);
            aux.setIdImagen(idFondos[i]);
            listaIP.add(aux);
        }
        return listaIP;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
