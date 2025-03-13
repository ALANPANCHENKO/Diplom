package com.example.galleryai_diplom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class FullScreenImageActivity extends AppCompatActivity {
    private static final String TAG = "FullScreenImageActivity";
    private ImageView fullScreenImageView;
    private Button confirmButton;
    private String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        //инициализация элементов интерфейса
        fullScreenImageView = findViewById(R.id.fullScreenImageView);
        confirmButton = findViewById(R.id.confirmButton);

        //получаем путь к изображению из Intent
        imagePath = getIntent().getStringExtra("imagePath");
        Log.d(TAG, "Полученный путь к изображению: " + imagePath);

        //проверяем, что путь к изображению не пустой
        if (imagePath == null || imagePath.isEmpty()) {
            Log.e(TAG, "Путь к изображению не передан или пустой");
            Toast.makeText(this, "Ошибка загрузки изображения", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем активность, если путь не передан
            return;
        }

        loadImageWithGlide(imagePath);

        confirmButton.setOnClickListener(v -> handleConfirmButtonClick());
    }

    private void loadImageWithGlide(String imagePath) {
        Glide.with(this)
                .load(imagePath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .override(1024, 1024)
                .into(fullScreenImageView);
    }

    private void handleConfirmButtonClick() {
        Log.d(TAG, "Подтверждение выбора изображения: " + imagePath);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("imagePath", imagePath);
        setResult(RESULT_OK, resultIntent);


        finish();
    }
}