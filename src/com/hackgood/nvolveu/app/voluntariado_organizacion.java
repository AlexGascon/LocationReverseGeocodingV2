package com.hackgood.nvolveu.app;

import java.io.Serializable;

/**
 * Created by Adrian on 04/04/2014.
 */
public class voluntariado_organizacion implements  Serializable{
    protected String fecha;
    protected String nombre_creador;
    protected String informacion;
    protected Double latitud_localizacion;
    protected Double longitud_localizacion;
    protected String titulo;
    protected int recordatorio;
    protected int cantidadMax;
    protected int cantidad;
    protected String categoria;
    protected String enfermedad;

    public voluntariado_organizacion(String fecha, String nombre_creador, String informacion, Double latitud_localizacion, Double longitud_localizacion, String titulo, int recordatorio, int cantidadMax, int cantidad, String categoria, String enfermedad) {
        this.fecha = fecha;
        this.nombre_creador = nombre_creador;
        this.informacion = informacion;
        this.latitud_localizacion = latitud_localizacion;
        this.longitud_localizacion = longitud_localizacion;
        this.titulo = titulo;
        this.recordatorio = recordatorio;
        this.cantidadMax = cantidadMax;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.enfermedad = enfermedad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre_creador() {
        return nombre_creador;
    }

    public void setNombre_creador(String nombre_creador) {
        this.nombre_creador = nombre_creador;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(int recordatorio) {
        this.recordatorio = recordatorio;
    }

    public int getCantidadMax() {
        return cantidadMax;
    }

    public void setCantidadMax(int cantidadMax) {
        this.cantidadMax = cantidadMax;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }



}
