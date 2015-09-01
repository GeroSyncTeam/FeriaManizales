package com.example.gero.feriapp.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gero.feriapp.R;

/**
 * Created by JULY92 on 24/08/2015.
 */
public class ViewHolderDias extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView dia;
    private TextView fecha;
    private ImageView fondoDia;
    private Context context;

    public ViewHolderDias(View itemView, Context context) {
        super(itemView);
        setDia((TextView) itemView.findViewById(R.id.dia));
        setFecha((TextView) itemView.findViewById(R.id.fecha));
        setFondoDia((ImageView) itemView.findViewById(R.id.imagenFondoDia));
        getFondoDia().setOnClickListener(this);
        this.context = context;
        itemView.setOnClickListener(this);
    }

    public TextView getDia() {
        return dia;
    }

    public void setDia(TextView dia) {
        this.dia = dia;
    }

    public TextView getFecha() {
        return fecha;
    }

    public void setFecha(TextView fecha) {
        this.fecha = fecha;
    }

    public ImageView getFondoDia() {
        return fondoDia;
    }

    public void setFondoDia(ImageView fondoDia) {
        this.fondoDia = fondoDia;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Item Clicked at " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}

