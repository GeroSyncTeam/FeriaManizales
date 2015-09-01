package com.example.gero.feriapp;


import android.app.ActionBar;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Gero on 17/08/2015.
 */


/**
 * Created by makone on 16/03/2015.
 */
public class fragmentDialogo extends DialogFragment {
    private PageAdapterVisorFotos PagAdapter; //ADAPTADOR
    private ViewPager viewPager;
    private int posicionViewPager;
    private Point dimenciones[];


    @Override
    public void onStart() {
        super.onStart();

// safety check
        if (getDialog() == null) {
            return;
        }
        //obtengo las dimenciones de la pantalla
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        getDialog().getWindow().setLayout((int) (width-(width*0.2)), (int) (height-(height*0.3)));


    }

    public void configurarDimenciones() {
        int dialogWidth = dimenciones[viewPager.getCurrentItem()].x;
        int dialogHeight = dimenciones[viewPager.getCurrentItem()].y;
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }

    public static fragmentDialogo getInstance(int[] idFotos) {
        fragmentDialogo adaptador = new fragmentDialogo();
        Bundle args = new Bundle();
        args.putStringArray("ID_FOTOS", toStringID(idFotos));
        adaptador.setArguments(args);

        return adaptador;
    }

    public static String[] toStringID(int[] idFotos) {
        String[] idFotosS = new String[idFotos.length];
        for (int i = 0; i < idFotos.length; i++) {
            idFotosS[i] = "" + idFotos[i];
        }

        return idFotosS;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //Configuracion Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        savedInstanceState = null;
        View view = inflater.inflate(R.layout.fragment_dialog, container);   //INFLAMOS CON LA VIEW
        FragmentManager fm = getChildFragmentManager();
        String idFotos[] = getArguments().getStringArray("ID_FOTOS");
        configurarDimensiones(idFotos);
        PagAdapter = new PageAdapterVisorFotos(fm, idFotos);  //CREAMOS ADAPTER
        viewPager = (ViewPager) view.findViewById(R.id.pagerVisorFotos);          //INICIAMOS PAGER
        viewPager.setAdapter(PagAdapter);
        viewPager.setCurrentItem(getPosicionViewPager());
        return view;
    }


    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public int getPosicionViewPager() {
        return posicionViewPager;
    }

    public void setPosicionViewPager(int posicionViewPager) {
        this.posicionViewPager = posicionViewPager;
    }

    public void configurarDimensiones(String idFotos[]) {
        dimenciones = new Point[idFotos.length];
        for (int i = 0; i < idFotos.length; i++) {
            Drawable d = getResources().getDrawable(getIdResourceByName(idFotos[i]));
            int w = d.getIntrinsicWidth();
            int h = d.getIntrinsicHeight();
            dimenciones[i] = new Point();
            dimenciones[i].set(w, h);
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
