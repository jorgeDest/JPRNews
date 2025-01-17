package com.fic.jprnews1_0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fic.jprnews1_0.Data.Apiservice;
import com.fic.jprnews1_0.Data.Retrofitistance;
import com.fic.jprnews1_0.Domain.UsuarioInteractor;
import com.fic.jprnews1_0.Model.Usuario;
import com.fic.jprnews1_0.Repository.UsuarioRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UsuarioInteractor usuarioInteractor;

    private List<Usuario> usuariolist;
    private Switch themeSwitch;
    private SharedPreferences sharedPreferences;

    private EditText etpass, etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etpass = findViewById(R.id.etpass);
        etemail = findViewById(R.id.etemail);

        Apiservice apiService = Retrofitistance.getRetrofitInstance().create(Apiservice.class);
        usuarioInteractor = new UsuarioInteractor(new UsuarioRepository(apiService));

        Button ButtonNavigate = findViewById(R.id.Login);
        ButtonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    public void Login() {
        String Email = etemail.getText().toString().trim();
        String password = etpass.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            etemail.setError("El Email es obligatorio");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etpass.setError("La contraseña es obligatoria");
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(Email);
        nuevoUsuario.setPassword(password);

        usuarioInteractor.login(nuevoUsuario, new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, PaginaLogin.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Sesión iniciada con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Email o contraseña incorrecta, por favor intente de nuevo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Conexión fallida", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
