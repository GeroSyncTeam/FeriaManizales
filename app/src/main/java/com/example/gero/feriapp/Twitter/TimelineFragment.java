package com.example.gero.feriapp.Twitter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by JULY92 on 03/09/15.
 */
public class TimelineFragment extends ListFragment {

    public static final String TWEETS_KEY ="tweets";
    OnListItemClickedCallbackHandler callbackHandler;

    // Las actividades que contengan este fragment pueden implementar esta interfaz para recibir
    // Eventos de cuando se hace click en un item
    public interface OnListItemClickedCallbackHandler{
        public void OnListItemClicked(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Obtenemos los tweets
        String[] tweets = getArguments().getStringArray(TWEETS_KEY);

        // El ArrayAdapter crear� la jerarquia de vistas a partir de un Array de Objetos
        // En este caso usaremos un layout que nos proporciona Android que simplemente mostrar�
        // La lista de Strings, el ultimo par�metro es el array con los objetos a mostrar
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                tweets
        );

        setListAdapter(arrayAdapter);


        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    // A este m�todo lo invoca Android en el momento en el que inserta el Fragment en la actividad
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // Intentamos castear la actividad, en caso de que salte la excepci�n significa que la actividad no implementa la interfaz
            // Por lo tanto no recibir� callbacks
            callbackHandler = (OnListItemClickedCallbackHandler) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Si la actividad implementa la interfaz llamamos al m�todo que tenga la actividad para manejar los eventos
        if(callbackHandler != null){
            callbackHandler.OnListItemClicked(position);
        }
    }
}
