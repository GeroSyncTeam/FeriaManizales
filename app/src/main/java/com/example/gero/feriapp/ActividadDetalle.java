package com.example.gero.feriapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

/**
 * Actividad que muestra la información de la candidata
 */
public class ActividadDetalle extends AppCompatActivity {

    private Candidata candidata;
    private int[] idFotosCandidata;

    private ImageView imagenExtendida;
    private TextView nombre_candidata;
    private TextView edad_candidata;
    private TextView medidas_candidata;
    private TextView estatura_candidata;
    private TextView peso_candidata;
    private TextView profesion_candidata;
    private TextView procedencia_candidata;
    private TextView calificacion_candidata;
    private Toolbar toolbar;

    private GridView gridView;
    private AdaptadorGridFotosDetalle adaptador;

    private String titulo;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actividad_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Se sobre escribe el funcionamiento del botón atras del action Bar
     *
     * @param item El botón del actionBar
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //acá cambia el idioma *********************************************************************
        SharedPreferences cambioIdioma = getSharedPreferences("cambioIdioma", Activity.MODE_PRIVATE);
        String sharedIdioma = cambioIdioma.getString("Lenguaje", "");
        String idiomaSO = Locale.getDefault().getLanguage();
        if (!sharedIdioma.equalsIgnoreCase("")) {
            if (!sharedIdioma.equalsIgnoreCase(idiomaSO)) {
                cambiarIdioma(sharedIdioma);
            }
        }
        //******************************************************************************************
        setContentView(R.layout.actividad_detalle);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (candidata == null) {
            // Obtener la Candidata con el identificador establecido en la actividad principal
            candidata = (Candidata) getIntent().getSerializableExtra("CANDIDATA");
            //candidata = Candidata.getItem(getIntent().getIntExtra("CANDIDATA", 1));
            cargarFotosCandidata();
        }
        titulo = getStringResourceByName("titulo_actividad_detalle") + " " + candidata.getPais();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(titulo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);
        nombre_candidata = (TextView) findViewById(R.id.nombre_candidata);
        edad_candidata = (TextView) findViewById(R.id.edad_candidata);
        medidas_candidata = (TextView) findViewById(R.id.medidas_candidata);
        estatura_candidata = (TextView) findViewById(R.id.estatura_candidata);
        peso_candidata = (TextView) findViewById(R.id.peso_candidata);
        profesion_candidata = (TextView) findViewById(R.id.profesion_candidata);
        procedencia_candidata = (TextView) findViewById(R.id.procedencia_candidata);
        calificacion_candidata = (TextView) findViewById(R.id.calificacion_candidata);

        // aca se parámetriza el griView que contiene las fotos de la candidata
        adaptador = new AdaptadorGridFotosDetalle(ActividadDetalle.this, idFotosCandidata);
        gridView = (GridView) findViewById(R.id.gridFotos);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                openDialog(position);
            }
        });
        gridView.setVerticalScrollBarEnabled(false);
        cargarImagenExtendida();
        cargarDatos();
    }

    /**
     * @param idioma es la sigla del idioma por el cual se va a cambiar ej en para inglés
     *               es para español
     */
    public void cambiarIdioma(String idioma) {
        CambiarIdioma cambiarIdioma = new CambiarIdioma(getBaseContext(), idioma);
        cambiarIdioma.changeLang(getSharedPreferences("cambioIdioma", Activity.MODE_PRIVATE));
        //String idiomaActual = getSharedPreferences("cambioIdioma", Activity.MODE_PRIVATE).getString("Lenguaje","ALGO POR DEFECTO");
    }

    /**
     * Despliega el FragmentDialogo en el que se visualizan las fotos de la candidata
     *
     * @param posicion
     */
    public void openDialog(int posicion) {
        //código del dialog que muestra las fotos
        fragmentDialogo overlay = fragmentDialogo.getInstance(idFotosCandidata);
        overlay.setPosicionViewPager(posicion);
        FragmentManager fm = getSupportFragmentManager();
        overlay.show(fm, "FragmentDialog");

    }

    /**
     * Carga y despliega la información de la candidata recuperando los string de las etiquetas
     * "edad","estatura"... mediante el método getStringResourceByName.
     */
    public void cargarDatos() {
        nombre_candidata.setText(candidata.getNombre());
        edad_candidata.setText(getStringResourceByName("edad") + " " + candidata.getEdad());
        medidas_candidata.setText(getStringResourceByName("medidas") + " " + candidata.getMedidas());
        estatura_candidata.setText(getStringResourceByName("estatura") + " " + candidata.getEstatura());
        peso_candidata.setText(getStringResourceByName("peso") + " " + candidata.getPeso());
        profesion_candidata.setText(getStringResourceByName("profesion") + " " + candidata.getProfesion());
        procedencia_candidata.setText(getStringResourceByName("procedencia") + " " + candidata.getProcedencia());
        calificacion_candidata.setText(getStringResourceByName("calificacion") + " " +candidata.getCalificacion());
    }

    /**
     * Carga la imagen de la candidata
     */
    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(getIdResourceByName(candidata.getIdDrawable()))
                .into(imagenExtendida);
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

    /**
     * @param aString identificador del objeto
     * @return el id numerico del objeto identificado con el aString
     */
    private int getIdResourceByName(String aString) {
        String packageName = "com.example.gero.feriapp";
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return resId;
    }


    public void cargarFotosCandidata() {
        idFotosCandidata = new int[candidata.getIdFotos().size()];
        for (int i = 0; i < candidata.getIdFotos().size(); i++) {
            idFotosCandidata[i] = getIdResourceByName(candidata.getIdFotos().get(i));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("test", "Start");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.v("test", "Restart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("test", "Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("test", "Pause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("test", "Stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("test", "Destroy");
    }
}