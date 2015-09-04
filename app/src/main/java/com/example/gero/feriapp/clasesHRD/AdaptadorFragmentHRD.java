package com.example.gero.feriapp.clasesHRD;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gero.feriapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gero on 30/08/2015.
 */
public class AdaptadorFragmentHRD extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorRVHRD adaptadorRVHRD;
    private static List<Establecimiento> items = new ArrayList<>();

    public static AdaptadorFragmentHRD getInstance(int posicion) {
        AdaptadorFragmentHRD adaptador = new AdaptadorFragmentHRD();
        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        adaptador.setArguments(args);
        return adaptador;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_hrd, container, false);
        //recyclerView ---------------------------------------
        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerViewHRD);
        adaptadorRVHRD = new AdaptadorRVHRD(getActivity(), getDatosHRD());
        recyclerView.setAdapter(adaptadorRVHRD);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public List<Establecimiento> getDatosHRD() {
        items.clear();
        String[] idFondos = {"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"};
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves"};
        String[] fechas = {"Primero", "Segundo", "Tercero", "Cuarto"};
        for (int i = 0; i < idFondos.length; i++) {
            Establecimiento aux = new Establecimiento();
            aux.setNombre(dias[i]);
            aux.setPreViewDescripcion(dias[i]);
            aux.setIdDrawable(idFondos[i]);
            List<String> idFotos = new ArrayList<String>();
            idFotos.add("drawable/mamasita");
            idFotos.add("drawable/mamasita2");
            idFotos.add("drawable/mamasita");
            idFotos.add("drawable/mamasita2");
            aux.setIdFotos(idFotos);
            aux.setUrl("www.cointproingenieria.com");
            aux.setDireccion("Calle falsa 123");
            aux.setTelefonos(idFotos);
            aux.setDescripcion("Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.");
            aux.setLike(true);
            items.add(aux);
        }
        return items;
    }

    public  static Establecimiento getItem(int id) {
        for (Establecimiento item : items) {
            Log.v("COMPROVANDO", ""+item.getId());
            if (item.getId() == id) {
                return item;
            }
        }
        Log.v("RETORNO NULL", "RETORNO UN ESTABLECIMIENTO NULO");
        return null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
