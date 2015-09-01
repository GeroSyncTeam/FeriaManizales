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
import android.widget.AdapterView;

import com.example.gero.feriapp.recyclerView.AdaptadorRVP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gero on 28/07/2015.
 * Es el Fragmente encargado de representar la interfaz del la vista de Programacion.
 */
public class AdaptadorFragmentProgramacion extends Fragment {

    private RecyclerView listaProgramacion;
    private AdaptadorRVP adaptadorRVP;


    public static AdaptadorFragmentProgramacion getInstance(int posicion) {
        AdaptadorFragmentProgramacion adaptador = new AdaptadorFragmentProgramacion();
        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        adaptador.setArguments(args);

        return adaptador;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_programacion, container, false);
        //recyclerView ---------------------------------------
        listaProgramacion = (RecyclerView) layout.findViewById(R.id.recyclerView);
        adaptadorRVP = new AdaptadorRVP(getActivity(), getDatosProgramacion());
        listaProgramacion.setAdapter(adaptadorRVP);
        listaProgramacion.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }


    public static List<InformacionProgramacion> getDatosProgramacion() {
        List<InformacionProgramacion> listaIP = new ArrayList<>();
        int[] idFondos = {R.drawable.header, R.drawable.header, R.drawable.header, R.drawable.header};
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves"};
        String[] fechas = {"Primero", "Segundo", "Tercero", "Cuarto"};
        for (int i = 0; i < idFondos.length; i++) {
            InformacionProgramacion aux = new InformacionProgramacion();
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