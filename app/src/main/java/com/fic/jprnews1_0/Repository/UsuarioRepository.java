package com.fic.jprnews1_0.Repository;

import com.fic.jprnews1_0.Data.Apiservice;
import com.fic.jprnews1_0.Model.Categoria;
import com.fic.jprnews1_0.Model.Noticia;
import com.fic.jprnews1_0.Model.Usuario;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UsuarioRepository {
    private static Apiservice apiservice;

    public UsuarioRepository(Apiservice apiservice) {
        this.apiservice = apiservice;

    }


    public void getUsuario(Callback<List<Usuario>> callback) {
        Call<List<Usuario>> call = apiservice.getUsuario();
        call.enqueue(callback);
    }

    public void getCategorias(Callback<List<Categoria>> callback) {
        Call<List<Categoria>> call = apiservice.getCategorias();
        call.enqueue(callback);
    }
    //Metodo CrearNoticia
    public void createNoticia(Noticia noticia, Callback<Noticia> callback) {
        Call<Noticia> call = apiservice.createNoticia(noticia);
        call.enqueue(callback);
    }
    //Metodo login
    public void login(Usuario usuario, Callback<Usuario> callback) {
        Call<Usuario> call = apiservice.login(usuario);
        call.enqueue(callback);
    }

    public void CreateUsario(Usuario usuario, Callback<Usuario> callback) {
        Call<Usuario> call = apiservice.CreateUsuario(usuario);
        call.enqueue(callback);
    }

    public void CargarNoticia(Callback<List<Noticia>> callback) {
        Call<List<Noticia>> call = apiservice.CargarNoticia();
        call.enqueue(callback);
    }
    //mostrar noticia

}