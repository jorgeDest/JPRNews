package com.fic.jprnews1_0.Model;

public class Categoria {
    private int CategoriaID;

    private String Nombre;


    public int getCategoriaID() {

        return CategoriaID;
    }

    public void setCategoriaID(int categoriaID) {

        this.CategoriaID =categoriaID;

    }

    public String getNombre() {

        return this.Nombre;
    }


    public String toString() {

        return this.Nombre;
    }
}

