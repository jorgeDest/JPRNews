package com.fic.jprnews1_0;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class addnews extends AppCompatActivity {


    private UsuarioInteractor usuarioInteractor;

    //Variable del modelo Categoria
    private Categoria CategoriaSeleccionada;
    //Arreglo de las categorias disponibles
    private List<Categoria> categoriaList;

    private Button btnupload_button;
    private EditText etContent_input,
            etAuthor_input,
            etTitle_input,
            etDate_input;
    //nombre del spiner
    private Spinner Category_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        etContent_input = findViewById(R.id.content_input);
        etAuthor_input = findViewById(R.id.author_input);
        etTitle_input = findViewById(R.id.title_input);
        etDate_input = findViewById(R.id.date_input);
        btnupload_button = findViewById(R.id.upload_button);
        Category_spinner = findViewById(R.id.category_spinner);

        Apiservice apiService = Retrofitistance.getRetrofitInstance().create(Apiservice.class);
        usuarioInteractor = new UsuarioInteractor(new UsuarioRepository(apiService));



        btnupload_button.setOnClickListener(v -> SubirNoticia());
        CargarCategorias();


    }

    private void SubirNoticia(){


        String Titulo = etTitle_input.getText().toString().trim();
        String Contenido = etContent_input.getText().toString().trim();
        String FechaPublicacion = etDate_input.getText().toString().trim();
        String Autor = etAuthor_input.getText().toString().trim();

        if (TextUtils.isEmpty(Titulo)) {
            etTitle_input.setError("El título es obligatorio");
            return;
        }

        if (TextUtils.isEmpty(Contenido)) {
            etContent_input.setError("El contenido es obligatorio");
            return;
        }

        if (TextUtils.isEmpty(FechaPublicacion)) {
            etDate_input.setError("La fecha publicacion es obligatoria");
            return;
        }

        if (TextUtils.isEmpty(Autor)) {
            etAuthor_input.setError("Ingrese el autor");
            return;
        }



        Noticia newNoticia = new Noticia();
        newNoticia.setTitulo(Titulo);
        newNoticia.setContenido(Contenido);
        newNoticia.setFechaPublicacion(FechaPublicacion);
        newNoticia.setCategoriaID(CategoriaSeleccionada.getCategoriaID());
        newNoticia.setNombre(Autor);  // Enviar el nombre del autor

        usuarioInteractor.createNoticia(newNoticia, new Callback<Noticia>() {
            @Override
            public void onResponse(Call<Noticia> call, Response<Noticia> response) {
                if (response.isSuccessful()){
                    Toast.makeText(addnews.this, "Noticia agregada correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(addnews.this, "La noticia no se creó exitosamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Noticia> call, Throwable t) {
                Toast.makeText(addnews.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void CargarCategorias() {
        usuarioInteractor.getCategorias(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoriaList = response.body();
                    CategorySpinner();
                } else {
                    Toast.makeText(addnews.this, "Error al cargar categorías", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(addnews.this, "Error de conexión:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void CategorySpinner() {
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category_spinner.setAdapter(adapter);

        Category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                CategoriaSeleccionada = categoriaList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                CategoriaSeleccionada = null;
            }
        });
    }

}