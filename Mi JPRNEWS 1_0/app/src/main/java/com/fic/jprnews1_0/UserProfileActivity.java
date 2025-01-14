package com.fic.jprnews1_0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class UserProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageProfile;
    private EditText etUsername;
    private Button btnChangePicture, btnSave;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageProfile = findViewById(R.id.imageProfile);
        etUsername = findViewById(R.id.etUsername);
        btnChangePicture = findViewById(R.id.btnChangePicture);
        btnSave = findViewById(R.id.btnSave);

        btnChangePicture.setOnClickListener(view -> openGallery());
        btnSave.setOnClickListener(view -> saveUsername());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al cargar imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveUsername() {
        String username = etUsername.getText().toString().trim();
        if (!username.isEmpty()) {
            Toast.makeText(this, "Nombre guardado: " + username, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor ingrese un nombre", Toast.LENGTH_SHORT).show();
        }
    }
}