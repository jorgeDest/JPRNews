package com.fic.jprnews1_0;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaCarga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pantalla_carga);



            Animation animacion1= AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
            Animation animacion2= AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

            TextView textView3 = findViewById(R.id.textView3);
            ImageView ImageLogoview = findViewById(R.id.ImageLogoview);

        ImageLogoview.setAnimation(animacion1);
            textView3.setAnimation(animacion2);



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PantallaCarga.this , CategoriasMain.class);
                    startActivity(intent);
                    finish();
                }
            }, 4000);
    }
}