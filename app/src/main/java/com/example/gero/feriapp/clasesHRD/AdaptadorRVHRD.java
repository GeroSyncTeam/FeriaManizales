package com.example.gero.feriapp.clasesHRD;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gero.feriapp.R;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gero on 30/08/2015.
 */
public class AdaptadorRVHRD extends RecyclerView.Adapter<ViewHolderHRD> {

    private LayoutInflater inflater;
    private Context context;
    private List<Establecimiento> items = Collections.emptyList();

    public AdaptadorRVHRD(Context context, List<Establecimiento> items) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolderHRD onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fila_hrd, parent, false);
        ViewHolderHRD viewHRD = new ViewHolderHRD(view);
        return viewHRD;
    }

    @Override
    public void onBindViewHolder(ViewHolderHRD holder, int position) {
        holder.getFoto().setImageResource(getIntResourceByName(items.get(position).getIdDrawable()));
        holder.getNombre().setText(items.get(position).getNombre());
        holder.getDescripcion().setText(items.get(position).getPreViewDescripcion());
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

    @Override
    public int getItemCount() {
        return items.size();
    }
}
