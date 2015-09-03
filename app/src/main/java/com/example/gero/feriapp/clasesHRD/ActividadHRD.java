package com.example.gero.feriapp.clasesHRD;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.gero.feriapp.AdaptadorGridFotosDetalle;
import com.example.gero.feriapp.CambiarIdioma;
import com.example.gero.feriapp.MyAdapterManager;
import com.example.gero.feriapp.R;
import com.example.gero.feriapp.SlidingTabLayout;
import com.example.gero.feriapp.fragmentDialogo;

import java.util.Locale;

/**
 * Created by Gero on 02/09/2015.
 */
public class ActividadHRD extends AppCompatActivity {

    private Establecimiento hrd;
    private int[] idFotosHRD;
    private GridView gridView;
    private AdaptadorGridFotosDetalle adaptador;
    private Toolbar toolbar;
    private String idioma;
    private String[] titulosTabs;
    /**
     * Aca van las tabs
     */
    private SlidingTabLayout mTabs;
    /**
     * Paginas que muestran la información en el inicio.
     */
    private ViewPager mPager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actividad_hrd, menu);
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //acá cambia el idioma *********************************************************************
        SharedPreferences cambioIdioma = getSharedPreferences("cambioIdioma", Activity.MODE_PRIVATE);
        String sharedIdioma = cambioIdioma.getString("Lenguaje", "");
        String idiomaSO = Locale.getDefault().getLanguage();
        idioma = idiomaSO;
        if (!sharedIdioma.equalsIgnoreCase("")) {
            if (!sharedIdioma.equalsIgnoreCase(idiomaSO)) {
                cambiarIdioma(sharedIdioma);
            }
        }
        //******************************************************************************************
        setContentView(R.layout.actividad_hrd);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (hrd == null) {
            // Obtener la Candidata con el identificador establecido en la actividad principal
            hrd = (Establecimiento) getIntent().getSerializableExtra("PARAMETROESTABLECIMIENTO");
            //candidata = Candidata.getItem(getIntent().getIntExtra("CANDIDATA", 1));
            cargarFotosHRD();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(hrd.getNombre());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // aca se parámetriza el griView que contiene las fotos de la candidata
        adaptador = new AdaptadorGridFotosDetalle(ActividadHRD.this, idFotosHRD);
        gridView = (GridView) findViewById(R.id.gridFotosHRD);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                openDialog(position);
            }
        });
        gridView.setVerticalScrollBarEnabled(false);
        //cargarImagenExtendida();
        //las paginas

        titulosTabs = getResources().getStringArray(R.array.titulos_tab_descHRD);//ESTO ES TEMPORAL

        mPager = (ViewPager) findViewById(R.id.pagerHRD);
        int g;
        mPager.setAdapter(new MyAdapterManager(10, getSupportFragmentManager(), titulosTabs, idioma,hrd.getId()));
        //mPager.setCurrentItem(miPosicion);
        //Los titulosTabs

        mTabs = (SlidingTabLayout) findViewById(R.id.tabsHRD);
        // mTabs.setBackgroundColor(getResources().getColor(R.color.primary));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.primary));

        //mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);


    }

    /**
     * Despliega el FragmentDialogo en el que se visualizan las fotos de la candidata
     *
     * @param posicion
     */
    public void openDialog(int posicion) {
        //código del dialog que muestra las fotos
        fragmentDialogo overlay = fragmentDialogo.getInstance(idFotosHRD);
        overlay.setPosicionViewPager(posicion);
        FragmentManager fm = getSupportFragmentManager();
        overlay.show(fm, "FragmentDialog");

    }

    /**
     * @param idioma es la sigla del idioma por el cual se va a cambiar ej en para inglés
     *               es para español
     */
    public void cambiarIdioma(String idioma) {
        CambiarIdioma cambiarIdioma = new CambiarIdioma(getBaseContext(), idioma);
        cambiarIdioma.changeLang(getSharedPreferences("cambioIdioma", Activity.MODE_PRIVATE));
        this.idioma = idioma;
        //String idiomaActual = getSharedPreferences("cambioIdioma", Activity.MODE_PRIVATE).getString("Lenguaje","ALGO POR DEFECTO");
    }

    public void cargarFotosHRD() {
        idFotosHRD = new int[hrd.getIdFotos().size()];
        for (int i = 0; i < hrd.getIdFotos().size(); i++) {
            idFotosHRD[i] = getIdResourceByName(hrd.getIdFotos().get(i));
        }
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

}
