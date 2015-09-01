package com.example.gero.feriapp.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gero.feriapp.informacionDias;
import com.example.gero.feriapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by JULY92 on 21/08/2015.
 */
public class AdaptadorRVD extends RecyclerView.Adapter<ViewHolderDias> {

    private LayoutInflater inflater;
    private List<informacionDias> datosDias = Collections.emptyList();
    private Context context;

    public AdaptadorRVD(Context context, List<informacionDias> datosDias) {
        inflater = LayoutInflater.from(context);
        this.datosDias = datosDias;
        this.context = context;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolderDias onCreateViewHolder(ViewGroup parent, int viewType) {
/*
        WindowManager wm = (WindowManager) parent.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int widthPixels = (int) (metrics.widthPixels*0.8);
*/
        View view = inflater.inflate(R.layout.fila_dias, parent, false);
        //int height = view.getLayoutParams().height;
        //view.setLayoutParams(new CardView.LayoutParams(widthPixels, height,2));
        ViewHolderDias viewHD = new ViewHolderDias(view, context);
        return viewHD;
    }

    @Override
    public void onBindViewHolder(ViewHolderDias holder, int position) {
        holder.getDia().setText(datosDias.get(position).getDia());
        holder.getFecha().setText(datosDias.get(position).getFecha());
        holder.getFondoDia().setImageResource(datosDias.get(position).getIdImagen());
    }


    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return datosDias.size();
    }

}

