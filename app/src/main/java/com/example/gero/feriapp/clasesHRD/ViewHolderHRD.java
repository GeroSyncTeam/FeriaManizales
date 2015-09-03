package com.example.gero.feriapp.clasesHRD;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gero.feriapp.ActividadDetalle;
import com.example.gero.feriapp.Candidata;
import com.example.gero.feriapp.R;

/**
 * Created by Gero on 30/08/2015.
 */
public class ViewHolderHRD extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView foto;
    private TextView nombre;
    private TextView descripcion;
    private Context context;
    private Establecimiento hrd;

    public ViewHolderHRD(View itemView, Context context) {
        super(itemView);
        this.context = context;
        setFoto((ImageView) itemView.findViewById(R.id.fotoHRD));
        setNombre((TextView) itemView.findViewById(R.id.nombreHRD));
        setDescripcion((TextView) itemView.findViewById(R.id.descripcionHRD));
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ActividadHRD.class);
        intent.putExtra("PARAMETROESTABLECIMIENTO", getHrd());
        //intent.putExtra("HASH_CODE",getHrd().getId());
        /**
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

         ActivityOptionsCompat activityOptions =
         ActivityOptionsCompat.makeSceneTransitionAnimation(
         this.getActivity(),
         new Pair<View, String>(view.findViewById(R.id.imagen_Candidata),
         ActividadDetalle.VIEW_NAME_HEADER_IMAGE)
         );

         ActivityCompat.startActivity(this.getActivity(), intent, activityOptions.toBundle());
         } else
         **/
        context.startActivity(intent);
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

    public Establecimiento getHrd() {
        return hrd;
    }

    public void setHrd(Establecimiento hrd) {
        this.hrd = hrd;
    }
}
