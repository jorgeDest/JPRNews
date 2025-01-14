package com.fic.jprnews1_0;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fic.jprnews1_0.Data.Apiservice;
import com.fic.jprnews1_0.Data.Retrofitistance;
import com.fic.jprnews1_0.Domain.UsuarioInteractor;
import com.fic.jprnews1_0.Model.Categoria;
import com.fic.jprnews1_0.Model.Noticia;
import com.fic.jprnews1_0.R;
import com.fic.jprnews1_0.Repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allnews extends AppCompatActivity {

    private UsuarioInteractor usuarioInteractor;
    private TextView txtNoticias;
    private TextView txtDetalleNoticia;
    private Button btnCargarNoticias;
    private Spinner CategorySpiner;

    private Noticia NoticiaSeleccionada;
    private List<Noticia> categoriaList;

    private List<Categoria> ListaCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnews);

        Apiservice apiService = Retrofitistance.getRetrofitInstance().create(Apiservice.class);
        usuarioInteractor = new UsuarioInteractor(new UsuarioRepository(apiService));

        btnCargarNoticias = findViewById(R.id.btnCargarNoti);
        CategorySpiner = findViewById(R.id.spinner_users);
        txtNoticias = findViewById(R.id.txtNoticias);
        txtDetalleNoticia = findViewById(R.id.txtDetalleNoticia);

        // Establecer el OnClickListener para el botón
        btnCargarNoticias.setOnClickListener(v -> CargarNoticias());
    }

    private void CargarNoticias() {
        usuarioInteractor.CargarNoticias(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoriaList = response.body();

                    // Crear una lista de usuarios únicos para el Spinner
                    List<String> usuariosUnicos = new ArrayList<>();
                    for (Noticia noticia : categoriaList) {
                        if (!usuariosUnicos.contains(noticia.getNombre())) {
                            usuariosUnicos.add(noticia.getNombre());
                        }
                    }

                    // Configurar el Spinner con usuarios únicos
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(allnews.this, android.R.layout.simple_spinner_item, usuariosUnicos);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    CategorySpiner.setAdapter(adapter);

                    // Configurar la funcionalidad del Spinner
                    CategorySpinner();
                } else {
                    Toast.makeText(allnews.this, "Error al cargar noticias", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Toast.makeText(allnews.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CategorySpinner() {
        CategorySpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener el nombre del usuario seleccionado (sin la cantidad de noticias)
                String usuarioSeleccionado = ((String) CategorySpiner.getSelectedItem()).split(" \\(")[0];

                // Filtrar noticias por el nombre del usuario seleccionado
                StringBuilder noticiasFiltradas = new StringBuilder();
                for (Noticia noticia : categoriaList) {
                    if (noticia.getNombre().equals(usuarioSeleccionado)) {
                        noticiasFiltradas.append("Titulo: ").append(noticia.getTitulo()).append("\n")
                                .append("Autor: ").append(noticia.getNombre()).append("\n")
                                .append("Fecha Publicación: ").append(noticia.getFechaPublicacion()).append("\n")
                                .append("Contenido: ").append(noticia.getContenido()).append("\n\n");
                    }
                }

                // Verificar si hay noticias filtradas
                if (noticiasFiltradas.length() > 0) {
                    txtDetalleNoticia.setVisibility(View.VISIBLE);
                    txtDetalleNoticia.setText(noticiasFiltradas.toString());
                } else {
                    txtDetalleNoticia.setVisibility(View.VISIBLE);
                    txtDetalleNoticia.setText("No hay noticias para este usuario.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                txtDetalleNoticia.setVisibility(View.VISIBLE);
                txtDetalleNoticia.setText("No se ha seleccionado ninguna noticia.");
            }
        });
    }
}