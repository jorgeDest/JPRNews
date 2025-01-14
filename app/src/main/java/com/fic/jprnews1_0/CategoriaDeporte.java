package com.fic.jprnews1_0;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fic.jprnews1_0.Data.Apiservice;
import com.fic.jprnews1_0.Data.Retrofitistance;
import com.fic.jprnews1_0.Domain.UsuarioInteractor;
import com.fic.jprnews1_0.Model.Noticia;
import com.fic.jprnews1_0.Repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaDeporte extends AppCompatActivity {

    private UsuarioInteractor usuarioInteractor;
    private TextView txtDetalleNoticia;
    private Button btnCargarNoti;
    private List<Noticia> categoriaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_deporte);

        // Inicializar elementos de la interfaz
        txtDetalleNoticia = findViewById(R.id.txtDetalleNoticia);
        btnCargarNoti = findViewById(R.id.btnCargarNoti);

        // Inicializar el servicio API y el interactor
        Apiservice apiService = Retrofitistance.getRetrofitInstance().create(Apiservice.class);
        usuarioInteractor = new UsuarioInteractor(new UsuarioRepository(apiService));

        // Configurar el evento OnClick para el botón
        btnCargarNoti.setOnClickListener(v -> CargarNoticias());
    }

    private void CargarNoticias() {
        usuarioInteractor.CargarNoticias(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoriaList = response.body();

                    // Filtrar noticias con CategoriaID == 0
                    List<Noticia> noticiasCategoria0 = new ArrayList<>();
                    for (Noticia noticia : categoriaList) {
                        if (noticia.getCategoriaID() == 0) { // Filtrar por CategoriaID 0
                            noticiasCategoria0.add(noticia);
                        }
                    }

                    if (noticiasCategoria0.isEmpty()) {
                        Toast.makeText(CategoriaDeporte.this, "No hay noticias de categoría 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Mostrar solo las noticias filtradas
                    StringBuilder noticiasFiltradas = new StringBuilder();
                    for (Noticia noticia : noticiasCategoria0) {
                        noticiasFiltradas.append("Titulo: ").append(noticia.getTitulo()).append("\n")
                                .append("Autor: ").append(noticia.getNombre()).append("\n")
                                .append("Fecha Publicación: ").append(noticia.getFechaPublicacion()).append("\n")
                                .append("Contenido: ").append(noticia.getContenido()).append("\n\n");
                    }

                    txtDetalleNoticia.setVisibility(View.VISIBLE);
                    txtDetalleNoticia.setText(noticiasFiltradas.toString());

                } else {
                    Toast.makeText(CategoriaDeporte.this, "Error al cargar noticias", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Toast.makeText(CategoriaDeporte.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}