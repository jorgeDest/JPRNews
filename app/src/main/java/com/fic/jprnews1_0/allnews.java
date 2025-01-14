package com.fic.jprnews1_0;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.jprnews1_0.Data.Apiservice;
import com.fic.jprnews1_0.Data.Retrofitistance;
import com.fic.jprnews1_0.Domain.UsuarioInteractor;
import com.fic.jprnews1_0.Model.Categoria;
import com.fic.jprnews1_0.Model.Noticia;
import com.fic.jprnews1_0.Model.Usuario;
import com.fic.jprnews1_0.Repository.UsuarioRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allnews extends AppCompatActivity {


    private UsuarioInteractor usuarioInteractor;
    private TextView txtNoticias;
    private Button btnCargarNoticias;

    private Spinner CategorySpiner;
    private Noticia NoticiaSeleccionada;

    private List<Noticia> categoriaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnews);

        Apiservice apiService = Retrofitistance.getRetrofitInstance().create(Apiservice.class);
        usuarioInteractor = new UsuarioInteractor(new UsuarioRepository(apiService));

        btnCargarNoticias = findViewById(R.id.btnCargarNoti);
        CategorySpiner = findViewById(R.id.spinner_users);


        txtNoticias = findViewById(R.id.txtNoticias);

        

        CargarNoticias();






    }
    private void CargarNoticias() {


        usuarioInteractor.CargarNoticias(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoriaList = response.body();
                    StringBuilder usuariosTexto = new StringBuilder();
                    for (Noticia noticia : categoriaList) {
                        usuariosTexto.append("Titulo: ").append(noticia.getTitulo()).append("\n")
                                .append("nombre: ").append(noticia.getNombre()).append("\n")
                                .append("Fecha publicacion: ").append(noticia.getFechaPublicacion()).append("\n")
                                .append("Contenido: ").append(noticia.getContenido()).append("\n \n");

                    }
                    txtNoticias.setText(usuariosTexto.toString());
                    CategorySpinner();
                } else {
                    Toast.makeText(allnews.this, "Error al cargar noticias", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Toast.makeText(allnews.this, "Error de conexión:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void CategorySpinner() {
        ArrayAdapter<Noticia> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategorySpiner.setAdapter(adapter);

        CategorySpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                NoticiaSeleccionada = categoriaList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                NoticiaSeleccionada = null;
            }
        });
    }




}