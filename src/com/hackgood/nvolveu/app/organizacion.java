package com.hackgood.nvolveu.app;

/**
 * Created by Adrian on 04/04/2014.
 */
public class organizacion {
    protected int id_organizacion;
    protected String nombre;
    protected String correo;
    protected String web;
    protected String imagen;
    protected String contacto;
    protected String informacion;


    public organizacion() {
        this.id_organizacion=0;
        this.nombre="";
        this.correo = "";
        this.web = "";
        this.contacto = "";
        this.informacion = "";
        this.imagen = "";

    }

    public organizacion( int id_organizacion,String nombre, String correo ,String imagen,String web,String contacto,String informacion ) {
        this.id_organizacion=id_organizacion;
        this.nombre=nombre;
        this.correo = correo;
        this.web = web;
        this.contacto = contacto;
        this.informacion = informacion;
        this.imagen = imagen;

    }

    public int getId_organizacion() {
        return id_organizacion;
    }

    public void setId_organizacion(int id_organizacion) {
        this.id_organizacion = id_organizacion;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}
