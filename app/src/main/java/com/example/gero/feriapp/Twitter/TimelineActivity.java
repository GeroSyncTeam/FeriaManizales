package com.example.gero.feriapp.Twitter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.gero.feriapp.R;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;

/**
 * Created by autentia on 16/12/13.
 */
//CAMBIÉ FragmentActivity por Fragment
public class TimelineActivity extends Fragment implements TimelineFragment.OnListItemClickedCallbackHandler {

    public static final String SELECTED_STATUS = "SelectedStatus";
    public static final String STATUSES = "Statuses";
    String[] tweets;
    ResponseList<Status> statuses;
    View layout;
    static int position;
//AGREGUE ESTE MÉTODO
    public static TimelineActivity getInstance(int posicion) {
        TimelineActivity adaptador = new TimelineActivity();
        Bundle args = new Bundle();
        args.putInt("posicion", posicion);
        adaptador.setArguments(args);

        return adaptador;
    }
    //AGREGUE ESTE MÉTODO
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.activity_timeline, container, false);

        Fragment fragment = this.getActivity().getSupportFragmentManager().findFragmentById(R.id.container);

        if (savedInstanceState != null) {
            statuses = (ResponseList<Status>) savedInstanceState.get(STATUSES);
        }

        if (fragment == null) {
            TweetRepository.getInstance().getTimelineAsync(timelineListener); // => timelineListener
        } else {
            View progressBar = layout.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
        }
        return layout;
    }
// ELIMINÉ ESTE MÉTODO
    /**
     * @Override protected void onCreate(Bundle savedInstanceState) {
     * super.onCreate(savedInstanceState);
     * setContentView(R.layout.activity_timeline);
     * <p/>
     * Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
     * <p/>
     * if (savedInstanceState != null) {
     * statuses = (ResponseList<Status>) savedInstanceState.get(STATUSES);
     * }
     * <p/>
     * if (fragment == null) {
     * TweetRepository.getInstance().getTimelineAsync(timelineListener); // => timelineListener
     * } else {
     * View progressBar = findViewById(R.id.progressBar);
     * progressBar.setVisibility(View.GONE);
     * }
     * }
     */
    TwitterListener timelineListener = new TwitterAdapter() {

        @Override
        public void gotHomeTimeline(ResponseList<Status> statuses) {
            showTimeline(statuses);
        }

        @Override
        public void onException(TwitterException te, TwitterMethod method) {
            showError();
        }
    };

    private void showError() {
        this.getActivity().runOnUiThread(new Runnable() { //AGREGUÉ this.getActivity()
            @Override
            public void run() {
                //AGREGUÉ this.getActivity()
                Toast.makeText(getActivity().getApplicationContext(), "No se ha podido obtener el Timeline", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showTimeline(ResponseList<Status> statuses) {
        // Cacheamos en memoria los Tweets
        this.statuses = statuses;

        // Creamos un array de Strings con el texto de los Status( Tweets )
        tweets = new String[statuses.size()];
        int counter = 0;
        for (Status status : statuses) {
            tweets[counter] = status.getText();
            counter++;
        }

        // Lo guardamos en un bundle, el cual le pasaremos al Fragment
        final Bundle bundle = new Bundle();
        bundle.putStringArray(TimelineFragment.TWEETS_KEY, tweets);

        // Debido a que el callback se esta ejecutando en otro Thread distinto al Thread de UI, necesitamos mandar un mensaje
        // Al Thread de UI para poder actualizar la vista, para ello usamos el metodo runOnUiThread de la clase Activity

        //AGREGUÉ this.getActivity()
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View progressBar = layout.findViewById(R.id.progressBar);//AGREGUÉ layout
                progressBar.setVisibility(View.GONE);
                // Creamos el TimelineFragment
                Fragment fragment = new TimelineFragment();
                // Añadimos el bundle con los tweets que hemos creado anteriormente
                fragment.setArguments(bundle);
                // Y lo insertamos en la vista contenedor
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
            }
        });
    }

    @Override
    public void OnListItemClicked(int position) {
        // Obtenemos el Status del array de Status(Tweet) que tenemos en memoria
        Status status = statuses.get(position);

        // Creamos un bundle que contendrá el Status(Tweet) seleccionado
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_STATUS, status);

        // Creamos el fragment
        Fragment fragment = new StatusFragment();
        fragment.setArguments(bundle);

        // Y lo añadimos al contenedor
        getActivity().getSupportFragmentManager() //AGREGUÉ this.getActivity()
                .beginTransaction()
                .replace(R.id.container, fragment) // Usamos replace porqué ya tenemos un fragment en el contenedor
                .addToBackStack("Status") // Haciendo esto permitimos que el usuario pueda volver al fragment anterior pulsando Back
                .commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {  //CAMBIÉ protected por public
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATUSES, statuses);
    }
}
