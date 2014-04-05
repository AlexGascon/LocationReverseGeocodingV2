package com.hackgood.nvolveu.app;

/**
 * Created by Adrian on 04/04/2014.
 */
public class Login {
    protected int id_persona;
    protected String nombre;
    protected String password;
    protected int automatico;

    public Login(int id_persona, String nombre, String password, int automatico) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.password = password;
        this.automatico = automatico;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAutomatico() {
        return automatico;
    }

    public void setAutomatico(int automatico) {
        this.automatico = automatico;
    }
}
