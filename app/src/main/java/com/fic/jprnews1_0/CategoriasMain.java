package com.fic.jprnews1_0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import androidx.cardview.widget.CardView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;
public class CategoriasMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_main);


            Switch switch1 = findViewById(R.id.switch1);
            // Recuperar preferencia guardada
            SharedPreferences preferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
            boolean isDarkMode = preferences.getBoolean("isDarkMode", false);
            switch1.setChecked(isDarkMode);
            setDarkMode(isDarkMode);

            // Configurar listener para el Switch
            switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                setDarkMode(isChecked);

                // Guardar preferencia del usuario
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isDarkMode", isChecked);
                editor.apply();
            });

    }
    private void setDarkMode(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void onCardClick(View view) {
        int id = view.getId();

        if (id == R.id.cardviewDeporte) {
            startActivity(new Intent(this, CategoriaDeporte.class));
        } else if (id == R.id.cardviewPoliciaca) {
            startActivity(new Intent(this, CategoriaPolicia.class));
        } else if (id == R.id.cardviewUas) {
            startActivity(new Intent(this, CategoriaUas.class));
        } else if (id == R.id.cardviewRegister) {
            startActivity(new Intent(this, Registro.class));
        } else {
            Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show();
        }
    }

}