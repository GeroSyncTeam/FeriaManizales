package com.example.gero.feriapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.gero.feriapp.clasesHRD.AdaptadorFragmentHRD;

/**
 * Created by Gero on 28/07/2015.
 * Es el manejador de los fragments que se despliegan en el ViewPager, donde se encuentran las páginas
 * La feria, Programación, Reinado, Concierto
 */
public class MyAdapterManager extends FragmentStatePagerAdapter {

    private String[] tabs;
    private String idioma;
    private int idTarea;

    /**
     *
     * @param idTarea representa el tipo
     * @param fm
     * @param tabs
     * @param idioma
     */
    public MyAdapterManager(int idTarea, FragmentManager fm, String[] tabs, String idioma) {
        super(fm);
        this.idTarea = idTarea;
        this.tabs = tabs;
        this.idioma = idioma;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabs[position];
    }



    /**
     * Returna el fragment asociado a una posición especifica.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        Log.v("Solicito el item ", "onCreate() Restoring previous state");
        Fragment adaptador = null;
        switch (idTarea){
            case 0:
                switch (position) {
                    case 0:
                        return adaptador = AdaptadorFragmentFeria.getInstance(position);
                    case 1:
                        return adaptador = AdaptadorFragmentProgramacion.getInstance(position);
                    case 2:
                        return adaptador = AdaptadorFragmentReinado.getInstance(position, idioma);
                    case 3:
                        return adaptador = AdaptadorFragmentFeria.getInstance(position);
                    case 4:
                        return adaptador = AdaptadorFragmentFeria.getInstance(position);
                    default:

                }
                break;

            case 1:
                switch (position) {
                    case 0:
                        return adaptador = AdaptadorFragmentHRD.getInstance(position);
                    case 1:
                        return adaptador = AdaptadorFragmentHRD.getInstance(position);
                    default:

                }
                break;
        }

        return null;
    }

/**
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
*/
    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return tabs.length;
    }
}
