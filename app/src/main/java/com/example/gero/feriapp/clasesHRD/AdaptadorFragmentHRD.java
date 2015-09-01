package com.example.gero.feriapp.clasesHRD;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gero.feriapp.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gero on 30/08/2015.
 */
public class AdaptadorFragmentHRD extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorRVHRD adaptadorRVHRD;

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
        adaptadorRVHRD = new AdaptadorRVHRD(getActivity(), getDatosProgramacion());
        recyclerView.setAdapter(adaptadorRVHRD);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<Establecimiento> getDatosProgramacion() {
        List<Establecimiento> listaIP = new ArrayList<>();
        String[] idFondos = {"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"};
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves"};
        String[] fechas = {"Primero", "Segundo", "Tercero", "Cuarto"};
        for (int i = 0; i < idFondos.length; i++) {
            Establecimiento aux = new Hotel();
            aux.setNombre(dias[i]);
            aux.setPreViewDescripcion(dias[i]);
            aux.setIdDrawable(idFondos[i]);
            listaIP.add(aux);
        }
        return listaIP;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
