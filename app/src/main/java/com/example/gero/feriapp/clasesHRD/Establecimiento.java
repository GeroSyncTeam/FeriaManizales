package com.example.gero.feriapp.clasesHRD;

import java.util.List;

/**
 * Created by Gero on 30/08/2015.
 */
public class Establecimiento {

    private List<String> idFotos;
    private String nombre;
    private String idDrawable;
    private String url;
    private String direccion;
    private List<String> telefonos;
    private String descripci�n;
    private String preViewDescripcion;
    private boolean like;

    public Establecimiento(){

    }

    public Establecimiento(List<String> idFotos, String nombre, String idDrawable, String url, String direccion, List<String> telefonos
            , String descripci�n, String preViewDescripcion,boolean like) {

        this.setIdFotos(idFotos);
        this.setNombre(nombre);
        this.setIdDrawable(idDrawable);
        this.setUrl(url);
        this.setDireccion(direccion);
        this.setTelefonos(telefonos);
        this.setDescripci�n(descripci�n);
        this.setPreViewDescripcion(preViewDescripcion);
        this.setLike(like);
    }

    public List<String> getIdFotos() {
        return idFotos;
    }

    public void setIdFotos(List<String> idFotos) {
        this.idFotos = idFotos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public String getDescripci�n() {
        return descripci�n;
    }

    public void setDescripci�n(String descripci�n) {
        this.descripci�n = descripci�n;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getPreViewDescripcion() {
        return preViewDescripcion;
    }

    public void setPreViewDescripcion(String preViewDescripcion) {
        this.preViewDescripcion = preViewDescripcion;
    }
}