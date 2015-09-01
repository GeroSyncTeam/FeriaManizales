package com.example.gero.feriapp;

import android.content.Context;
import android.util.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la existencia de un Candidata
 */
public class Candidata implements Serializable {

    private double calificacion;
    private String idDrawable;
    private List<String> idFotos;
    private String nombre;
    private String pais;
    private String edad;
    private String medidas;
    private String estatura;
    private String peso;
    private String profesion;
    private String procedencia;

    public Candidata(String nombre, String idDrawable, String pais, String edad,
                     String medidas, String estatura, String peso, String profesion, String procedencia, List<String> idFotos,double calificacion) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.pais = pais;
        this.edad = edad;
        this.medidas = medidas;
        this.estatura = estatura;
        this.calificacion = calificacion;
        this.setPeso(peso);
        this.profesion = profesion;
        this.procedencia = procedencia;
        this.setIdFotos(idFotos);
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static List<Candidata> readJsonStreamCandidatas(Context context, String idioma) throws IOException {
        InputStream in = new BufferedInputStream(context.getAssets().open("candidatas.json"));
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "ISO-8859-1"));
        try {
            // Leer Array
            return leerArrayCandidatas(reader, idioma);
        } finally {
            reader.close();
        }

    }

    private static List leerArrayCandidatas(JsonReader reader, String idioma) throws IOException {
        // Lista temporal
        ArrayList arrayListCandidatas = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            Candidata candidata;
            if (idioma.equalsIgnoreCase("es")) {
                candidata = leerCandidataES(reader);
            } else {
                candidata = leerCandidataEN(reader);
            }
            // Leer objeto
            arrayListCandidatas.add(candidata);
        }
        reader.endArray();
        return arrayListCandidatas;
    }

    private static Candidata leerCandidataES(JsonReader reader) throws IOException {

        double calificacion = 0;
        String idDrawable = null;
        ArrayList<String> idFotos = new ArrayList<String>();
        String nombre = null;
        String pais = null;
        String edad = null;
        String medidas = null;
        String estatura = null;
        String peso = null;
        String profesion = null;
        String procedencia = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "nombre":
                    nombre = reader.nextString();
                    break;
                case "calificacion":
                    calificacion = reader.nextDouble();
                    break;
                case "medidas":
                    medidas = reader.nextString();
                    break;
                case "idDrawable":
                    idDrawable = reader.nextString();
                    break;
                case "idFotos":
                    reader.beginArray();
                    while (reader.hasNext()) {
                        idFotos.add(reader.nextString());
                    }
                    reader.endArray();
                    break;
                case "estatura":
                    estatura = reader.nextString();
                    break;
                case "peso":
                    peso = reader.nextString();
                    break;
                case "paisES":
                    pais = reader.nextString();
                    break;
                case "edadES":
                    edad = reader.nextString();
                    break;
                case "profesionES":
                    profesion = reader.nextString();
                    break;
                case "procedenciaES":
                    procedencia = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Candidata(nombre, idDrawable, pais, edad, medidas, estatura, peso, profesion, procedencia, idFotos,calificacion);

    }

    private static Candidata leerCandidataEN(JsonReader reader) throws IOException {

        double calificacion = 0;
        String idDrawable = null;
        ArrayList<String> idFotos = new ArrayList<String>();
        String nombre = null;
        String pais = null;
        String edad = null;
        String medidas = null;
        String estatura = null;
        String peso = null;
        String profesion = null;
        String procedencia = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "nombre":
                    nombre = reader.nextString();
                    break;
                case "calificacion":
                    calificacion = reader.nextDouble();
                    break;
                case "medidas":
                    medidas = reader.nextString();
                    break;
                case "idDrawable":
                    idDrawable = reader.nextString();
                    break;
                case "idFotos":
                    reader.beginArray();
                    while (reader.hasNext()) {
                        idFotos.add(reader.nextString());
                    }
                    reader.endArray();
                    break;
                case "estatura":
                    estatura = reader.nextString();
                    break;
                case "peso":
                    peso = reader.nextString();
                    break;
                case "paisEN":
                    pais = reader.nextString();
                    break;
                case "edadEN":
                    edad = reader.nextString();
                    break;
                case "profesionEN":
                    profesion = reader.nextString();
                    break;
                case "procedenciaEN":
                    procedencia = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Candidata(nombre, idDrawable, pais, edad, medidas, estatura, peso, profesion, procedencia, idFotos,calificacion);

    }
/*
    public static Candidata[] ITEMS = {
            new Candidata("Jaguar F-Type 2015", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("Mercedes AMG-GT", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("Mazda MX-5", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("Porsche 911 GTS", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("BMW Serie 6", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("Ford Mondeo", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("Volvo V60 Cross Country", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("Jaguar XE", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("VW Golf R Variant", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
            new Candidata("Seat León ST Cupra", "drawable/mamasita", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", "Colombia", new String[]{"drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita", "drawable/mamasita"}),
    };
*/

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public List<String> getIdFotos() {
        return idFotos;
    }

    public void setIdFotos(List<String> idFotos) {
        this.idFotos = idFotos;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}