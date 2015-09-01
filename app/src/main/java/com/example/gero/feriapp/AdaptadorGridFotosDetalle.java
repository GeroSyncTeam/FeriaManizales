package com.example.gero.feriapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Gero on 16/08/2015.
 */
public class AdaptadorGridFotosDetalle extends BaseAdapter {

    private Context context;
    private int[] idFotosCandidata;

    public AdaptadorGridFotosDetalle(Context context,int [] idFotosCandidata){
        this.context = context;
        this.idFotosCandidata = idFotosCandidata;
    }

    @Override
    public int getCount() {
        return idFotosCandidata.length;
    }

    @Override
    public Integer getItem(int position) {
        return idFotosCandidata[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_detalle, viewGroup, false);
        }

        ImageView fotoCandidata = (ImageView) view.findViewById(R.id.fotoCandidataDetalle);


        final int item = getItem(position);
        Glide.with(fotoCandidata.getContext())
                .load(item)
                .into(fotoCandidata);

        return view;
    }
}
