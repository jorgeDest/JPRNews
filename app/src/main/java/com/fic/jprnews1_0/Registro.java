package com.fic.jprnews1_0;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.jprnews1_0.Data.Apiservice;
import com.fic.jprnews1_0.Data.Retrofitistance;
import com.fic.jprnews1_0.Domain.UsuarioInteractor;
import com.fic.jprnews1_0.Model.Noticia;
import com.fic.jprnews1_0.Model.Usuario;
import com.fic.jprnews1_0.Repository.UsuarioRepository;

import java.security.PublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {

    private EditText etNombre,
                        etCorreo,
                        etPassword;
    private Button btnRegistro;
    private Usuario UsuarioID;

    private UsuarioInteractor usuarioInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etPassword = findViewById(R.id.etPassword);
        etCorreo = findViewById(R.id.etCorreo);
        btnRegistro = findViewById(R.id.btnRegistro);

        Apiservice apiService = Retrofitistance.getRetrofitInstance().create(Apiservice.class);
        usuarioInteractor = new UsuarioInteractor(new UsuarioRepository(apiService));


        btnRegistro.setOnClickListener(v -> CreateUsuario());



        Button ButtonNavigate = findViewById(R.id.LoginButton);
        ButtonNavigate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this,  MainActivity.class);
                startActivity(intent);

            }
        });


    }
    public void CreateUsuario(){

        String Nombre = etNombre.getText().toString().trim();
        String Correo = etCorreo.getText().toString().trim();
        String Password = etPassword.getText().toString().trim();


        if (TextUtils.isEmpty(Nombre)) {
            etNombre.setError("El Nombre es obligatorio");
            return;
        }

        if (TextUtils.isEmpty(Correo)) {
            etCorreo.setError("El Correo es obligatorio");
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            etPassword.setError("La Contraseña es obligatoria");
            return;
        }

        Usuario newUsuario = new Usuario();
        newUsuario.setNombre(Nombre);
        newUsuario.setEmail(Correo);
        newUsuario.setPassword(Password);



        usuarioInteractor.CreateUsuario(newUsuario, new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(Registro.this, PaginaLogin.class);
                    startActivity(intent);
                    Toast.makeText(Registro.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Registro.this, "El usuario no se pudo crear, intente de nuevo " , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Toast.makeText(Registro.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}