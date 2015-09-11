package com.example.gero.feriapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by PracG on 05/08/2015.
 */
public class AdaptadorFragmentReinado extends android.support.v4.app.Fragment {

    private GridPersonal gridView;
    private AdaptadorDeCandidatas adaptador;
    private OnFragmentListadoListener mListener;
    private String idioma;

    public static AdaptadorFragmentReinado getInstance(int posicion, String idioma) {
        AdaptadorFragmentReinado adaptador = new AdaptadorFragmentReinado();
        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        args.putString("idioma", idioma);
        adaptador.setArguments(args);

        return adaptador;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (GridPersonal) getView().findViewById(R.id.grid);//grid
        adaptador = new AdaptadorDeCandidatas(this.getActivity(), getStringResourceByName("titulo_actividad_detalle"),getArguments().getString("idioma"));//aca debe estar la lista de candidatas
        gridView.setAdapter(adaptador);
        gridView.setExpanded(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                evento(parent, view, position, id);

            }
        });
    }


    public void evento(AdapterView parent, View view, int position, long id) {
        Candidata item = (Candidata) parent.getItemAtPosition(position);
        //Candidata item= Candidata.getItem(0);
        Intent intent = new Intent(this.getActivity(), ActividadDetalle.class);
        intent.putExtra("CANDIDATA", item);
        /**
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

         ActivityOptionsCompat activityOptions =
         ActivityOptionsCompat.makeSceneTransitionAnimation(
         this.getActivity(),
         new Pair<View, String>(view.findViewById(R.id.imagen_Candidata),
         ActividadDetalle.VIEW_NAME_HEADER_IMAGE)
         );

         ActivityCompat.startActivity(this.getActivity(), intent, activityOptions.toBundle());
         } else
         **/
        startActivity(intent);
    }

    /**
     * @param aString identificador del objeto
     * @return el contenido del objeto identificado con el aString
     */
    private String getStringResourceByName(String aString) {
        String packageName = "com.example.gero.feriapp";
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentListadoListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentListadoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentListadoListener {
        public void onListViewInteraction();
    }

}
