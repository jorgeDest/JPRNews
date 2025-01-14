package com.fic.jprnews1_0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaginaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_login);
        
    }

    public void onCardClick(View view) {
        int id = view.getId();

        if (id == R.id.cardviewNoticias) {
            startActivity(new Intent(this, CategoriasMain.class));
        } else if (id == R.id.CardviewDatosPersonales) {
            startActivity(new Intent(this, perfil.class));
        } else if (id == R.id.CardviewPreferencias) {
            startActivity(new Intent(this, addnews.class));
        } else if (id == R.id.CardviewInfo) {
            startActivity(new Intent(this, Registro.class));
        } else {
            Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show();
        }
    }
}