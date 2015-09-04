package com.example.gero.feriapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.Locale;


public class Inicio extends AppCompatActivity implements AdaptadorFragmentReinado.OnFragmentListadoListener {


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private String idioma;
    /**
     * Aca van las tabs
     */
    private SlidingTabLayout mTabs;
    /**
     * Paginas que muestran la información en el inicio.
     */
    private ViewPager mPager;
    private int miPosicion = 0;
    private int idTarea = 0;

    /**
     * Guarda la posición del slidingTab para cargarla en una nueva ejecución
     *
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("mMyCurrentPosition", mPager.getCurrentItem());
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        miPosicion = savedInstanceState.getInt("mMyCurrentPosition");
        // where mMyCurrentPosition should be a public value in your activity.
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
        idioma = idiomaSO;
        if (!sharedIdioma.equalsIgnoreCase("")) {
            if (!sharedIdioma.equalsIgnoreCase(idiomaSO)) {
                cambiarIdioma(sharedIdioma);
            }
        }
        //******************************************************************************************
        setContentView(R.layout.activity_inicio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (savedInstanceState != null) {
            miPosicion = savedInstanceState.getInt("mMyCurrentPosition");
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_activity_inicio);
        setSupportActionBar(toolbar);


        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
/*
        if (navigationView != null) {
            if(idiomaSO.equalsIgnoreCase("es")){

                navigationView.getMenu().findItem(R.id.item_navigation_drawer_idioma).setIcon(R.drawable.en);
            }else{
                navigationView.getMenu().findItem(R.id.item_navigation_drawer_idioma).setIcon(R.drawable.ic_action_content_send);
            }
            setupNavigationDrawerContent(navigationView);
        }
        */
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if (!sharedIdioma.equalsIgnoreCase("")) {
            if (sharedIdioma.equalsIgnoreCase("en")) {
                navigationView.getMenu().findItem(R.id.item_navigation_drawer_idioma).setIcon(R.drawable.ic_action_content_send);
            } else {
                navigationView.getMenu().findItem(R.id.item_navigation_drawer_idioma).setIcon(R.drawable.en);
            }
        } else {
            if (idiomaSO.equalsIgnoreCase("en")) {
                navigationView.getMenu().findItem(R.id.item_navigation_drawer_idioma).setIcon(R.drawable.ic_action_content_send);
            } else {
                navigationView.getMenu().findItem(R.id.item_navigation_drawer_idioma).setIcon(R.drawable.en);
            }
        }

        setupNavigationDrawerContent(navigationView);

        cambiarActivity(null);

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

    /**
     * @param titulosTabs
     */
    public void cambiarActivity(String[] titulosTabs) {
        if (titulosTabs == null) {
            titulosTabs = getResources().getStringArray(R.array.titulos_tab_feria);
        }
        //las paginas

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapterManager(idTarea, getSupportFragmentManager(), titulosTabs, idioma));
        mPager.setCurrentItem(miPosicion);
        //Los titulosTabs

        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        // mTabs.setBackgroundColor(getResources().getColor(R.color.primary));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.primary));
        //mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Se configura el contenido del navigationDrawer
     *
     * @param navigationView
     */
    private void setupNavigationDrawerContent(final NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        String[] titulosTabs;
                        //textView = (TextView) findViewById(R.id.textView);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_feria:
                                menuItem.setChecked(true);
                                titulosTabs = getResources().getStringArray(R.array.titulos_tab_feria);
                                idTarea = 0;
                                cambiarActivity(titulosTabs);
                                //Toast.makeText(Inicio.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_hoteles:
                                menuItem.setChecked(true);
                                titulosTabs = getResources().getStringArray(R.array.titulos_tab_hoteles);
                                idTarea = 1;
                                cambiarActivity(titulosTabs);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                //Intent intent = new Intent(Inicio.this, Hoteles.class);
                                //startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_restaurantes:
                                menuItem.setChecked(true);
                                titulosTabs = getResources().getStringArray(R.array.titulos_tab_restaurantes);
                                idTarea = 2;
                                cambiarActivity(titulosTabs);
                                //textView.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_bares:
                                menuItem.setChecked(true);
                                titulosTabs = getResources().getStringArray(R.array.titulos_tab_discotecas);
                                //idTarea = 3;
                                //cambiarActivity(titulosTabs);
                                Intent intent = new Intent(Inicio.this, Hoteles.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(GravityCompat.START);

                                return true;
                            case R.id.item_navigation_drawer_idioma:
                                String idiomaSO = Locale.getDefault().getLanguage();
                                if (idiomaSO.equalsIgnoreCase("es")) {
                                    menuItem.setIcon(R.drawable.ic_action_content_send);
                                    menuItem.setChecked(false);
                                    navigationView.getMenu().findItem(R.id.item_navigation_drawer_idiomaP).setChecked(false);
                                    cambiarIdioma("en");
                                } else {
                                    menuItem.setIcon(R.drawable.en);
                                    menuItem.setChecked(true);
                                    navigationView.getMenu().findItem(R.id.item_navigation_drawer_idiomaP).setChecked(true);
                                    cambiarIdioma("es");
                                }
                                restartApp();

                                return true;
                        }
                        return true;
                    }
                });
    }


    public void restartApp() {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    @Override
    public void onListViewInteraction() {

    }


}
