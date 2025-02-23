//package com.example.galleryai_diplom;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import com.github.chrisbanes.photoview.PhotoView;
//import com.squareup.picasso.Picasso;
//
//public class ZoomableImageActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_zoomable_image);
//
//        PhotoView photoView = findViewById(R.id.photo_view);
//
//        // Получаем путь к изображению из Intent
//        String imagePath = getIntent().getStringExtra("image_path");
//
//        // Загружаем изображение в PhotoView с помощью Picasso
//        Picasso.get().load("file://" + imagePath).into(photoView);
//    }
//}
