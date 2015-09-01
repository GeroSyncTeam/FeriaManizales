package com.example.gero.feriapp.clasesHRD;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gero.feriapp.R;

/**
 * Created by Gero on 30/08/2015.
 */
public class ViewHolderHRD extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView foto;
    private TextView nombre;
    private TextView descripcion;

    public ViewHolderHRD(View itemView) {
        super(itemView);
        setFoto((ImageView) itemView.findViewById(R.id.fotoHRD));
        setNombre((TextView) itemView.findViewById(R.id.nombreHRD));
        setDescripcion((TextView) itemView.findViewById(R.id.descripcionHRD));
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextView descripcion) {
        this.descripcion = descripcion;
    }
}
