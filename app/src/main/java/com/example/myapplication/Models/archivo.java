package com.example.myapplication.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class archivo {
    public String nombre;
    public String descripcion;

    public archivo(JSONObject a) throws JSONException {
        this.nombre = a.getString("nombre").toString();
        this.descripcion = a.getString("descripcion").toString();
    }
    //Metodo para parsear los datos
    public static ArrayList<archivo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<archivo> archivo = new ArrayList<>();
        for (int i = 0; i < datos.length() && i<20; i++) {
            archivo.add(new archivo(datos.getJSONObject(i)));
        }
        return archivo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
