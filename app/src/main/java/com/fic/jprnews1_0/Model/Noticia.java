package com.fic.jprnews1_0.Model;

public class Noticia {

    private int CategoriaID;
    private String Titulo,
            FechaPublicacion,
            Contenido,
            Nombre;
    private int UsuarioID;

    /////////////////////////////////
    /* nuestros getters y setters */
    ///////////////////////////////


    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    public int getCategoriaID() {
        return CategoriaID;
    }

    public void setCategoriaID(int categoriaID) {
        this.CategoriaID = categoriaID;
    }

    public String getFechaPublicacion() {
        return FechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.FechaPublicacion = fechaPublicacion;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        this.Contenido = contenido;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
}

