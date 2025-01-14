package com.fic.jprnews1_0.Domain;

import com.fic.jprnews1_0.Model.Categoria;
import com.fic.jprnews1_0.Model.Noticia;
import com.fic.jprnews1_0.Model.Usuario;
import com.fic.jprnews1_0.Repository.UsuarioRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class UsuarioInteractor {
    private final UsuarioRepository usuarioRepository;

    //constructor
    public UsuarioInteractor(UsuarioRepository usuarioRepository){

        this.usuarioRepository = usuarioRepository;

    }

    public void getUsuario(Callback<List<Usuario>> callback) {
        usuarioRepository.getUsuario(callback);
    }


    public void getCategorias(Callback<List<Categoria>> callback) {
        usuarioRepository.getCategorias(callback);
    }

    public void createNoticia(Noticia noticia,Callback<Noticia> callback) {
        usuarioRepository.createNoticia(noticia,callback);
    }


    public void login(Usuario usuario, Callback<Usuario> callback) {
        usuarioRepository.login(usuario, callback);
    }

    public void CreateUsuario(Usuario usuario,Callback<Usuario > callback){
        usuarioRepository.CreateUsario(usuario,callback);

    }

    public void CargarNoticias(Callback<List<Noticia>> callback){
        usuarioRepository.CargarNoticia(callback);
    }

}
