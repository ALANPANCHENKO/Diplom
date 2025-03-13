//package com.example.galleryai_diplom;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FaceSearchActivity extends AppCompatActivity implements ImageAdapter.OnImageClickListener {
//    private RecyclerView recyclerView;
//    private ImageAdapter adapter;
//    private List<String> images;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_face_search);
//
//        //получаем список изображений из Intent
//        images = getIntent().getStringArrayListExtra("images");
//        if (images == null) {
//            images = new ArrayList<>(); // Если список пуст, создаем новый
//        }
//
//        // настройка RecyclerView
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        adapter = new ImageAdapter(this, images, this); // Передаем this как слушатель
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public void onImageClick(String imagePath) {
//        Log.d("MainActivity", "Выбрано изображение: " + imagePath);
//
//        // Проверяем, что путь к изображению не пустой
//        if (imagePath == null || imagePath.isEmpty()) {
//            Toast.makeText(this, "Ошибка: путь к изображению не найден", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Открываем изображение на полный экран
//        Intent intent = new Intent(this, FullScreenImageActivity.class);
//        intent.putExtra("imagePath", imagePath);
//        startActivity(intent);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            // Пользователь подтвердил выбор, запускаем поиск
//            String selectedImagePath = data != null ? data.getStringExtra("imagePath") : null;
//            if (selectedImagePath != null) {
//                // Запуск ИИ для поиска похожих изображений
//                List<String> similarImages = findSimilarImages(selectedImagePath);
//
//                // Отображение результатов поиска
//                Intent resultIntent = new Intent(this, SearchResultsActivity.class);
//                resultIntent.putStringArrayListExtra("similarImages", new ArrayList<>(similarImages));
//                startActivity(resultIntent);
//            }
//        }
//    }
//
//    // Метод для поиска похожих изображений (заглушка)
//    private List<String> findSimilarImages(String imagePath) {
//        // Здесь будет логика поиска с использованием OpenCV или другой библиотеки
//        Log.d("FaceSearch", "Поиск похожих изображений для: " + imagePath);
//        return new ArrayList<>(); // Возвращаем пустой список для примера
//    }
//}