package com.example.galleryai_diplom;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GalleryManager {
    private final Context context;
    private final RecyclerView recyclerView;
    private final ImageAdapter.OnImageClickListener listener;
    private List<String> images;

    public GalleryManager(Context context, RecyclerView recyclerView, ImageAdapter.OnImageClickListener listener) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.listener = listener;
    }

    public void loadImages() {
        images = ImageUtils.loadImages_utils(context);

        if (images == null || images.isEmpty()) {
            Toast.makeText(context, "Нет изображений", Toast.LENGTH_SHORT).show();
            return;
        }


        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(new ImageAdapter(context, images, listener));
    }

    public void openFullScreenImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            Toast.makeText(context, "Ошибка: путь к изображению не найден", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("GalleryManager", "Открытие изображения: " + imagePath);


        if (!new java.io.File(imagePath).exists()) {
            Toast.makeText(context, "Изображение не найдено", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(context, FullScreenImageActivity.class);
        intent.putExtra("imagePath", imagePath);
        context.startActivity(intent);
    }
}