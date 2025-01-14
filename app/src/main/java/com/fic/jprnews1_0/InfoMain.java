package com.fic.jprnews1_0;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InfoMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info_main);



    }
    public void onCardClick(View view) {
        int id = view.getId();

        if (id == R.id.cardQuieneSomos) {
            startActivity(new Intent(this, Quiensomos.class));
        }
        else if (id == R.id.cardContacto) {
            startActivity(new Intent(this, Contactanos.class));
        }  else {
            Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show();
        }
    }
}