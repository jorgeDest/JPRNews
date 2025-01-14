package com.fic.jprnews1_0.Model;

import com.fic.jprnews1_0.Data.Apiservice;
import com.fic.jprnews1_0.Repository.UsuarioRepository;

public class Usuario {
    private int UsuarioID;
    private String Nombre,Email,password;


    ////////////
    /* nuestros getters */
    public int getUsuarioID() {
        return this.UsuarioID;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getPassword() {

        return this.password;
    }

    ////////////////////////
    /* nuestros setters */
    ////////////////////////
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setUsuarioID(int usuarioID) {
        this.UsuarioID = usuarioID;
    }
}
