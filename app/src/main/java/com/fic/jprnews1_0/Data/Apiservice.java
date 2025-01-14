package com.fic.jprnews1_0.Data;


import com.fic.jprnews1_0.Model.Categoria;
import com.fic.jprnews1_0.Model.Noticia;
import com.fic.jprnews1_0.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apiservice {

    //Metodos encargados de hacer nuestra llamada a nuestra api
    @GET("Usuario")
    Call <List<Usuario>> getUsuario();




    @GET("Categorias")
    Call<List<Categoria>> getCategorias();

    @POST("SubirNoticia")
    Call<Noticia> createNoticia(@Body Noticia noticia);

    @POST("login")
    Call<Usuario> login(@Body Usuario usuario);

    @POST("register")
    Call <Usuario> CreateUsuario(@Body Usuario usuario);

    @GET
    Call<List<Noticia>> CargarNoticia();
}
