package com.example.gero.feriapp.recyclerView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gero.feriapp.informacionDias;
import com.example.gero.feriapp.InformacionProgramacion;
import com.example.gero.feriapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Gero on 02/08/2015.
 * Adaptador Recycler View Programación se implementa los items del RecyclerView en el cual
 * se va a desplegar la programación de la feria
 */
public class AdaptadorRVP extends RecyclerView.Adapter<ViewHolderProgramacion>{

    private LayoutInflater inflater;
    private List<InformacionProgramacion> datosProgramacion = Collections.emptyList();
    private Context context;

    public AdaptadorRVP(Context context,List<InformacionProgramacion> datosProgramacion){
        inflater = LayoutInflater.from(context);
        this.datosProgramacion = datosProgramacion;
        this.context = context;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolderProgramacion onCreateViewHolder(ViewGroup parent, int viewType) {
/*
        WindowManager wm = (WindowManager) parent.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int widthPixels = (int) (metrics.widthPixels*0.8);
*/
        View view = inflater.inflate(R.layout.fila_programacion, parent, false);
        //int height = view.getLayoutParams().height;
        //view.setLayoutParams(new CardView.LayoutParams(widthPixels, height,2));
        ViewHolderProgramacion viewHP = new ViewHolderProgramacion(view, context);
        return viewHP;
    }


    @Override
    public void onBindViewHolder(ViewHolderProgramacion holder, int position) {

        holder.getDia().setText(datosProgramacion.get(position).getDia());
        holder.getFecha().setText(datosProgramacion.get(position).getFecha());
        holder.getFondoDia().setImageResource(datosProgramacion.get(position).getIdImagen());

    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return datosProgramacion.size();
    }
}
