package com.hackgood.nvolveu.app;

/**
 * Created by Adrian on 04/04/2014.
 */
public class persona {
    protected int id_persona;
    protected String nombre;
    protected String correo;
    protected int puntuacion;
    protected String imagen;
    protected Double latitud_localizacion;
    protected Double longitud_localizacion;
    protected Double latitud_casa;
    protected Double longitud_casa;

    public persona() {
        this.id_persona=0;
        this.nombre="";
        this.correo = "";
        this.puntuacion = 0;
        this.imagen = "";
        this.latitud_localizacion= 0.0;
        this.longitud_localizacion= 0.0;
        this.latitud_casa= 0.000;
        this.longitud_casa= 0.000;
    }

    public persona( int id_persona,String nombre, String correo , int puntuacion,String imagen, Double latitud_localizacion, Double longitud_localizacion,Double latitud_casa,Double longitud_casa) {
        this.id_persona=id_persona;
        this.nombre=nombre;
        this.correo = correo;
        this.puntuacion = puntuacion;
        this.imagen = imagen;
        this.latitud_localizacion= latitud_localizacion;
        this.longitud_localizacion= longitud_localizacion;

        if(latitud_casa==0)
            this.latitud_casa= 0.00;
        else
        this.latitud_casa= latitud_casa;

        if(longitud_casa==0)
            this.longitud_casa= 0.00;
        else
        this.longitud_casa= longitud_casa;
    }

    public persona( int id_persona,String nombre, String correo , int puntuacion,String imagen) {
        this.id_persona=id_persona;
        this.nombre=nombre;
        this.correo = correo;
        this.puntuacion = puntuacion;
        this.imagen = imagen;
        this.latitud_localizacion= 0.0;
        this.longitud_localizacion= 0.0;
        this.latitud_casa= 0.0;
        this.longitud_casa= 0.0;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getLatitud_localizacion() {
        return latitud_localizacion;
    }

    public void setLatitud_localizacion(Double latitud_localizacion) {
        this.latitud_localizacion = latitud_localizacion;
    }

    public Double getLongitud_localizacion() {
        return longitud_localizacion;
    }

    public void setLongitud_localizacion(Double longitud_localizacion) {
        this.longitud_localizacion = longitud_localizacion;
    }

    public Double getLatitud_casa() {
        return latitud_casa;
    }

    public void setLatitud_casa(Double latitud_casa) {
        this.latitud_casa = latitud_casa;
    }

    public Double getLongitud_casa() {
        return longitud_casa;
    }

    public void setLongitud_casa(Double longitud_casa) {
        this.longitud_casa = longitud_casa;
    }
}
