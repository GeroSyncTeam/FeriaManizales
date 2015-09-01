package com.example.gero.feriapp.clasesHRD;

import java.util.List;

/**
 * Created by Gero on 30/08/2015.
 */
public class Hotel extends Establecimiento {

    public Hotel() {

    }

    public Hotel(List<String> idFotos, String nombre, String idDrawable, String url, String direccion, List<String> telefonos, String descripción, String preViewDescripcion, boolean like) {
        super(idFotos, nombre, idDrawable, url, direccion, telefonos, descripción, preViewDescripcion, like);
    }
}
