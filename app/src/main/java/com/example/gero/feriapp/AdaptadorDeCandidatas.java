package com.example.gero.feriapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

/**
 * {@link BaseAdapter} para poblar Candidatas en un grid view
 */

public class AdaptadorDeCandidatas extends BaseAdapter {

    private Context context;
    private List<Candidata> listaCandidatas;
    private String titulo;

    public AdaptadorDeCandidatas(Context context, String titulo, String idioma) {
        this.context = context;
        this.titulo = titulo;
        try {
            this.listaCandidatas = Candidata.readJsonStreamCandidatas(context,idioma);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return listaCandidatas.size();
    }

    @Override
    public Candidata getItem(int position) {
        return listaCandidatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenCandidata = (ImageView) view.findViewById(R.id.imagen);
        TextView nombreCandidata = (TextView) view.findViewById(R.id.nombre);
        TextView tituloCandidata = (TextView) view.findViewById(R.id.descripcion);


        Candidata item = getItem(position);
        imagenCandidata.setImageResource(getIntResourceByName(item.getIdDrawable()));
        nombreCandidata.setText(item.getNombre());
        tituloCandidata.setText(titulo+" "+item.getPais());


        return view;
    }

    /**
     * @param aString identificador del objeto
     * @return el id del objeto identificado con el aString
     */
    private int getIntResourceByName(String aString) {
        String packageName = "com.example.gero.feriapp";
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        return resId;
    }

}
