package com.example.galleryai_diplom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.recyclerview.widget.GridLayoutManager;//для отображения списка
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import android.os.Bundle;
import android.widget.Toast;//вывод кратковременных уведомлений

import java.util.List;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity { //AppCompatActivity позволяет использовать
    //стандартные возможности Android
    private static final int REQUEST_PERMISSIONS = 101;
    private RecyclerView recyclerView; //переменная для отображения изображений
    private ImageAdapter adapter; //адаптер, который свяжет данные с RycyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //подгрузка интерфейса

        recyclerView = findViewById(R.id.recyclerView); //находит recyclerView в разметке
        // по его идентификатору

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { //проверка версии Android
            checkAndRequestPermissions();
        } else {
            setupGallery();
        }
    }

    private void checkAndRequestPermissions() {
        //создание массива разрешений
        String[] permissions = {
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
        };

        boolean allGranted = true;
        //цикл проходит по каждому разрешению и с помощью checkSelfPermission проверяет,
        //предоставлено ли разрешение.
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }
        //если не все разршения предоставлены, вызывается ActivityCompat.requestPermissions, который
        //отображает диалог запроса разрешений пользователю
        if (!allGranted) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
        } else {
            setupGallery();
        }
    }


    //метод вызывается после того, как пользователь ответил на запрос разрешений
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                setupGallery();
            } else {
                Toast.makeText(this, "Необходимо разрешение для работы с медиафайлами", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupGallery(){
        List<String> images = ImageUtils.loadImages(this);//возвращает список строк, которые
        //находятся на устройстве.
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//устанавливает менеджер компановки
        //создает сетку с 2 столбцами
        adapter = new ImageAdapter(this, images);
        recyclerView.setAdapter(adapter);

        Toast.makeText(this, "Все разрешения предоставлены", Toast.LENGTH_SHORT).show();
    }
}

