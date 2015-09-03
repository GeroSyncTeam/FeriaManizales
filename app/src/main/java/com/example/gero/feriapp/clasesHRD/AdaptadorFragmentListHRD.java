package com.example.gero.feriapp.clasesHRD;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gero.feriapp.R;

/**
 * Created by Gero on 02/09/2015.
 */
public class AdaptadorFragmentListHRD extends Fragment {

    private TextView tituloWebHRD;
    private TextView tituloTelHRD;
    private TextView tituloDirHRD;
    private TextView tituloDetallesHRD;
    private TextView detallesHRD;

    private ImageView iconoWebHRD;
    private ImageView iconoTelHRD;
    private ImageView iconoDirHRD;

    private Establecimiento hrd;

    public static AdaptadorFragmentListHRD getInstance(int posicion, int idHrd) {
        AdaptadorFragmentListHRD adaptador = new AdaptadorFragmentListHRD();
        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        args.putInt("ESTABLECIMIENTO", idHrd);
        adaptador.setArguments(args);

        return adaptador;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_list_hrd, container, false);
        tituloWebHRD = (TextView) layout.findViewById(R.id.tituloWebHRD);
        tituloTelHRD = (TextView) layout.findViewById(R.id.tituloTelHRD);
        tituloDirHRD = (TextView) layout.findViewById(R.id.tituloDirHRD);
        tituloDetallesHRD = (TextView) layout.findViewById(R.id.tituloDetallesHRD);
        detallesHRD = (TextView) layout.findViewById(R.id.detallesHRD);

        iconoWebHRD = (ImageView) layout.findViewById(R.id.iconoWebHRD);
        iconoTelHRD = (ImageView) layout.findViewById(R.id.iconoTelHRD);
        iconoDirHRD = (ImageView) layout.findViewById(R.id.iconoDirHRD);
        cargarDatos();
        return layout;
    }

    public void cargarDatos() {
        hrd = (Establecimiento) AdaptadorFragmentHRD.getItem(getArguments().getInt("ESTABLECIMIENTO")); //Aca es donde debo recibir el establecimiento
        tituloWebHRD.setText(hrd.getUrl());
        tituloTelHRD.setText(hrd.getTelefonos().get(0));
        tituloDirHRD.setText(hrd.getDireccion());
        detallesHRD.setText(hrd.getDescripción());
/*
        tituloDetallesHRD .setText("Detalles");
        iconoWebHRD = (ImageView) layout.findViewById(R.id.iconoWebHRD);
        iconoTelHRD = (ImageView) layout.findViewById(R.id.iconoTelHRD);
        iconoDirHRD = (ImageView) layout.findViewById(R.id.iconoDirHRD);
*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
